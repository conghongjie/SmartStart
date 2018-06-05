

# SmartStart

Android智能启动框架

### 框架特性：

#### 1、支持依赖关系

#### 2、多样化任务：可选择延时任务、IO任务

1）支持延时加载：ApplicationTask会在Application.onCreate()结束前执行完，DelayTask可以配置延后执行。

2）支持区分io任务和cpu任务

#### 3、智能多线程异步：根据CPU核数控制线程池大小

线程池过大，会增加多线程的切换时间；线程池过小，则不能充分利用CPU，SmartStart的实现机制：

1）Application任务执行时，会根据建立一个cpu线程池（限制cpu核数个线程）和一个的io线程池（无限制）

2）Delay任务执行时，会建立一个共用线程池（限制cpu核数-2个线程）

#### 4、自学习优先级能力：根据上一次启动的任务链耗时，计算本次启动每个任务的优先级

App的启动任务拆解后，任务之间的依赖关系构成一个依赖图，最长的耗时路径可能决定着App的启动耗时。当线程池执行完一个任务后，如果有n个无依赖的任务需要被执行，一旦我们没有选择那个最高优先级的任务去执行，就一定会拉长最长耗时路径的执行总时间，那么App的启动时长就会增加，所以我们的关键问题是，如何对任务设置优先级。

SmartStart的核心思想是通过构建上一次启动的依赖图，将任务所在的所有路径中最长路径的耗时值作为此任务下次启动的优先级。具体算法请阅读代码。

#### 5、方便systrace查看：我们在每个任务执行时加入Trace标记

此处有systrace图



### 框架接入：

1、增加依赖：

    compile 'com.elvis.android:smart_start:1.0.0'


1、实现一个启动接口：

    public static IModuleStart lockScreenModuleStart = new IModuleStart() {
        @Override
        public void buildTasks(ArrayList<AbsTask> tasks) {
            tasks.add(new ApplicationCPUTask(StartConstant.LockScreen_ApplicationTask_1)
                            .setExecutor(new AbsTask.Executor() {
                                @Override
                                public void execute() {
                                    Test.doJob(200);
                                }
                            })
            );
            tasks.add(new DelayCPUTask(StartConstant.LockScreen_DelayTask_1)
                            .setExecutor(new AbsTask.Executor() {

                                @Override
                                public void execute() {
                                    Test.doJob(200);
                                }
                            })
            );
            tasks.add(new DelayCPUTask(StartConstant.LockScreen_DelayTask_2)
                            .addDepend(StartConstant.LockScreen_DelayTask_1)
                            .addDepend(StartConstant.Article_ApplicationTask_2)
                            .addDepend(StartConstant.Article_DelayTask_2)
                            .setExecutor(new AbsTask.Executor() {
                                @Override
                                public void execute() {
                                    Test.doJob(100);
                                    Test.doSleep(500);
                                    Test.doJob(100);
                                }
                            })
            );
        }
    };
  
2、在Application的相关位置调用SmartStart接口：

    public class MyApplication extends Application {

        private static Handler mainHandler = new Handler(Looper.getMainLooper());

        @Override
        protected void attachBaseContext(Context base) {
            super.attachBaseContext(base);

            // 初始化启动器
            SmartStart.setContext(base);
            // 构建启动器
            SmartStart.addModuleStart(LockScreenManager.lockScreenModuleStart);
            SmartStart.addModuleStart(ArticleManager.articleModuleStart);
            ...
            // 开始执行ApplicationTasks
            SmartStart.startApplicationTasks();
        }
    
        @Override
        public void onCreate() {
            super.onCreate();
            // 等待ApplicationTasks执行结束
            SmartStart.waitUntilApplicationTasksFinish();
            // 5s后开始执行DelayTasks
            mainHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    SmartStart.startDelayTasks();
                }
            },5000);
        }
    }