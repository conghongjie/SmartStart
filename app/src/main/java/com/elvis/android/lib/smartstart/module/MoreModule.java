package com.elvis.android.lib.smartstart.module;

import com.elvis.android.lib.smart_start.obj.AbsTask;
import com.elvis.android.lib.smart_start.obj.ApplicationCPUTask;
import com.elvis.android.lib.smart_start.obj.IModuleStart;
import com.elvis.android.lib.smartstart.StartConstant;
import com.elvis.android.lib.smartstart.Test;

import java.util.ArrayList;


/**
 * Created by conghongjie on 2018/6/5.
 */

public class MoreModule {






    public static IModuleStart Module_0 = new IModuleStart() {
        @Override
        public void buildTasks(ArrayList<AbsTask> tasks) {
            tasks.add(new ApplicationCPUTask(StartConstant.Module_01)
                    .setExecutor(new AbsTask.Executor() {
                        @Override
                        public void execute() {
                            Test.doJob(200);
                        }
                    })
            );
            tasks.add(new ApplicationCPUTask(StartConstant.Module_02)
                    .setExecutor(new AbsTask.Executor() {
                        @Override
                        public void execute() {
                            Test.doJob(200);
                        }
                    })
            );
            tasks.add(new ApplicationCPUTask(StartConstant.Module_03)
                    .setExecutor(new AbsTask.Executor() {
                        @Override
                        public void execute() {
                            Test.doJob(200);
                        }
                    })
            );
            tasks.add(new ApplicationCPUTask(StartConstant.Module_04)
                    .addDepend(StartConstant.Module_01)
                    .setExecutor(new AbsTask.Executor() {
                        @Override
                        public void execute() {
                            Test.doJob(200);
                        }
                    })
            );
        }
    };

    public static IModuleStart Module_1 = new IModuleStart() {
        @Override
        public void buildTasks(ArrayList<AbsTask> tasks) {
            tasks.add(new ApplicationCPUTask(StartConstant.Module_11)
                    .setExecutor(new AbsTask.Executor() {
                        @Override
                        public void execute() {
                            Test.doJob(200);
                        }
                    })
            );
            tasks.add(new ApplicationCPUTask(StartConstant.Module_12)
                    .setExecutor(new AbsTask.Executor() {
                        @Override
                        public void execute() {
                            Test.doJob(200);
                        }
                    })
            );
            tasks.add(new ApplicationCPUTask(StartConstant.Module_13)
                    .setExecutor(new AbsTask.Executor() {
                        @Override
                        public void execute() {
                            Test.doJob(200);
                        }
                    })
            );
            tasks.add(new ApplicationCPUTask(StartConstant.Module_14)
                    .setExecutor(new AbsTask.Executor() {
                        @Override
                        public void execute() {
                            Test.doJob(200);
                        }
                    })
            );
            tasks.add(new ApplicationCPUTask(StartConstant.Module_15)
                    .setExecutor(new AbsTask.Executor() {
                        @Override
                        public void execute() {
                            Test.doJob(200);
                        }
                    })
            );
        }
    };
    public static IModuleStart Module_2 = new IModuleStart() {
        @Override
        public void buildTasks(ArrayList<AbsTask> tasks) {
            tasks.add(new ApplicationCPUTask(StartConstant.Module_21)
                    .addDepend(StartConstant.Module_04)
                    .setExecutor(new AbsTask.Executor() {
                        @Override
                        public void execute() {
                            Test.doJob(200);
                        }
                    })
            );
            tasks.add(new ApplicationCPUTask(StartConstant.Module_22)
                    .setExecutor(new AbsTask.Executor() {
                        @Override
                        public void execute() {
                            Test.doJob(200);
                        }
                    })
            );
            tasks.add(new ApplicationCPUTask(StartConstant.Module_23)
                    .addDepend(StartConstant.Module_21)
                    .setExecutor(new AbsTask.Executor() {
                        @Override
                        public void execute() {
                            Test.doJob(200);
                        }
                    })
            );
            tasks.add(new ApplicationCPUTask(StartConstant.Module_24)
                    .setExecutor(new AbsTask.Executor() {
                        @Override
                        public void execute() {
                            Test.doJob(200);
                        }
                    })
            );
            tasks.add(new ApplicationCPUTask(StartConstant.Module_25)
                    .setExecutor(new AbsTask.Executor() {
                        @Override
                        public void execute() {
                            Test.doJob(200);
                        }
                    })
            );
            tasks.add(new ApplicationCPUTask(StartConstant.Module_26)
                    .addDepend(StartConstant.Module_12)
                    .setExecutor(new AbsTask.Executor() {
                        @Override
                        public void execute() {
                            Test.doJob(200);
                        }
                    })
            );
        }
    };

    public static IModuleStart Module_3 = new IModuleStart() {
        @Override
        public void buildTasks(ArrayList<AbsTask> tasks) {
            tasks.add(new ApplicationCPUTask(StartConstant.Module_31)
                    .setExecutor(new AbsTask.Executor() {
                        @Override
                        public void execute() {
                            Test.doJob(200);
                        }
                    })
            );
            tasks.add(new ApplicationCPUTask(StartConstant.Module_32)
                    .setExecutor(new AbsTask.Executor() {
                        @Override
                        public void execute() {
                            Test.doJob(200);
                        }
                    })
            );
            tasks.add(new ApplicationCPUTask(StartConstant.Module_33)
                    .setExecutor(new AbsTask.Executor() {
                        @Override
                        public void execute() {
                            Test.doJob(200);
                        }
                    })
            );
            tasks.add(new ApplicationCPUTask(StartConstant.Module_34)
                    .setExecutor(new AbsTask.Executor() {
                        @Override
                        public void execute() {
                            Test.doJob(200);
                        }
                    })
            );
        }
    };


    public static IModuleStart Module_4 = new IModuleStart() {
        @Override
        public void buildTasks(ArrayList<AbsTask> tasks) {
            tasks.add(new ApplicationCPUTask(StartConstant.Module_41)
                    .addDepend(StartConstant.Module_31)
                    .addDepend(StartConstant.Module_22)
                    .setExecutor(new AbsTask.Executor() {
                        @Override
                        public void execute() {
                            Test.doJob(200);
                        }
                    })
            );
        }
    };


    public static IModuleStart Module_5 = new IModuleStart() {
        @Override
        public void buildTasks(ArrayList<AbsTask> tasks) {
            tasks.add(new ApplicationCPUTask(StartConstant.Module_51)
                    .addDepend(StartConstant.Article_ApplicationTask_1)
                    .setExecutor(new AbsTask.Executor() {
                        @Override
                        public void execute() {
                            Test.doJob(200);
                        }
                    })
            );
            tasks.add(new ApplicationCPUTask(StartConstant.Module_52)
                    .addDepend(StartConstant.Module_33)
                    .addDepend(StartConstant.Module_34)
                    .setExecutor(new AbsTask.Executor() {
                        @Override
                        public void execute() {
                            Test.doJob(200);
                        }
                    })
            );

        }
    };

    public static IModuleStart Module_6 = new IModuleStart() {
        @Override
        public void buildTasks(ArrayList<AbsTask> tasks) {
            tasks.add(new ApplicationCPUTask(StartConstant.Module_61)
                    .addDepend(StartConstant.Module_15)
                    .setExecutor(new AbsTask.Executor() {
                        @Override
                        public void execute() {
                            Test.doJob(200);
                        }
                    })
            );
            tasks.add(new ApplicationCPUTask(StartConstant.Module_62)
                    .addDepend(StartConstant.Module_24)
                    .addDepend(StartConstant.Module_31)
                    .addDepend(StartConstant.Module_61)
                    .addDepend(StartConstant.Module_12)
                    .setExecutor(new AbsTask.Executor() {
                        @Override
                        public void execute() {
                            Test.doJob(200);
                        }
                    })
            );

        }
    };


    public static IModuleStart Module_7 = new IModuleStart() {
        @Override
        public void buildTasks(ArrayList<AbsTask> tasks) {
            tasks.add(new ApplicationCPUTask(StartConstant.Module_71)
                    .addDepend(StartConstant.Module_51)
                    .setExecutor(new AbsTask.Executor() {
                        @Override
                        public void execute() {
                            Test.doJob(200);
                        }
                    })
            );
            tasks.add(new ApplicationCPUTask(StartConstant.Module_72)
                    .setExecutor(new AbsTask.Executor() {
                        @Override
                        public void execute() {
                            Test.doJob(200);
                        }
                    })
            );

        }
    };

    public static IModuleStart Module_8 = new IModuleStart() {
        @Override
        public void buildTasks(ArrayList<AbsTask> tasks) {
            tasks.add(new ApplicationCPUTask(StartConstant.Module_81)
                    .addDepend(StartConstant.Module_34)
                    .addDepend(StartConstant.Module_41)
                    .addDepend(StartConstant.Module_52)
                    .addDepend(StartConstant.Module_13)
                    .setExecutor(new AbsTask.Executor() {
                        @Override
                        public void execute() {
                            Test.doJob(200);
                        }
                    })
            );
            tasks.add(new ApplicationCPUTask(StartConstant.Module_82)
                    .addDepend(StartConstant.Module_81)
                    .addDepend(StartConstant.Module_33)
                    .addDepend(StartConstant.Module_72)
                    .setExecutor(new AbsTask.Executor() {
                        @Override
                        public void execute() {
                            Test.doJob(200);
                        }
                    })
            );

        }
    };

    public static IModuleStart Module_9 = new IModuleStart() {
        @Override
        public void buildTasks(ArrayList<AbsTask> tasks) {
            tasks.add(new ApplicationCPUTask(StartConstant.Module_91)
                    .addDepend(StartConstant.Module_71)
                    .setExecutor(new AbsTask.Executor() {
                        @Override
                        public void execute() {
                            Test.doJob(200);
                        }
                    })
            );
        }
    };






    public static IModuleStart Module_a = new IModuleStart() {
        @Override
        public void buildTasks(ArrayList<AbsTask> tasks) {
            tasks.add(new ApplicationCPUTask(StartConstant.Module_a1)
                    .setExecutor(new AbsTask.Executor() {
                        @Override
                        public void execute() {
                            Test.doJob(200);
                        }
                    })
            );
            tasks.add(new ApplicationCPUTask(StartConstant.Module_a2)
                    .addDepend(StartConstant.Module_91)
                    .setExecutor(new AbsTask.Executor() {
                        @Override
                        public void execute() {
                            Test.doJob(200);
                        }
                    })
            );
            tasks.add(new ApplicationCPUTask(StartConstant.Module_a3)
                    .setExecutor(new AbsTask.Executor() {
                        @Override
                        public void execute() {
                            Test.doJob(200);
                        }
                    })
            );
            tasks.add(new ApplicationCPUTask(StartConstant.Module_a4)
                    .setExecutor(new AbsTask.Executor() {
                        @Override
                        public void execute() {
                            Test.doJob(200);
                        }
                    })
            );
        }
    };

    public static IModuleStart Module_b = new IModuleStart() {
        @Override
        public void buildTasks(ArrayList<AbsTask> tasks) {
            tasks.add(new ApplicationCPUTask(StartConstant.Module_b1)
                    .setExecutor(new AbsTask.Executor() {
                        @Override
                        public void execute() {
                            Test.doJob(200);
                        }
                    })
            );
            tasks.add(new ApplicationCPUTask(StartConstant.Module_b2)
                    .setExecutor(new AbsTask.Executor() {
                        @Override
                        public void execute() {
                            Test.doJob(200);
                        }
                    })
            );
            tasks.add(new ApplicationCPUTask(StartConstant.Module_b3)
                    .setExecutor(new AbsTask.Executor() {
                        @Override
                        public void execute() {
                            Test.doJob(200);
                        }
                    })
            );
            tasks.add(new ApplicationCPUTask(StartConstant.Module_b4)
                    .setExecutor(new AbsTask.Executor() {
                        @Override
                        public void execute() {
                            Test.doJob(200);
                        }
                    })
            );
            tasks.add(new ApplicationCPUTask(StartConstant.Module_b5)
                    .addDepend(StartConstant.Module_a2)
                    .setExecutor(new AbsTask.Executor() {
                        @Override
                        public void execute() {
                            Test.doJob(200);
                        }
                    })
            );
        }
    };
    public static IModuleStart Module_c = new IModuleStart() {
        @Override
        public void buildTasks(ArrayList<AbsTask> tasks) {
            tasks.add(new ApplicationCPUTask(StartConstant.Module_c1)
                    .addDepend(StartConstant.Module_11)
                    .setExecutor(new AbsTask.Executor() {
                        @Override
                        public void execute() {
                            Test.doJob(200);
                        }
                    })
            );
            tasks.add(new ApplicationCPUTask(StartConstant.Module_c2)
                    .setExecutor(new AbsTask.Executor() {
                        @Override
                        public void execute() {
                            Test.doJob(200);
                        }
                    })
            );
            tasks.add(new ApplicationCPUTask(StartConstant.Module_c3)
                    .setExecutor(new AbsTask.Executor() {
                        @Override
                        public void execute() {
                            Test.doJob(200);
                        }
                    })
            );
            tasks.add(new ApplicationCPUTask(StartConstant.Module_c4)
                    .addDepend(StartConstant.Module_11)
                    .setExecutor(new AbsTask.Executor() {
                        @Override
                        public void execute() {
                            Test.doJob(200);
                        }
                    })
            );
            tasks.add(new ApplicationCPUTask(StartConstant.Module_c5)
                    .setExecutor(new AbsTask.Executor() {
                        @Override
                        public void execute() {
                            Test.doJob(200);
                        }
                    })
            );
            tasks.add(new ApplicationCPUTask(StartConstant.Module_c6)
                    .addDepend(StartConstant.Module_12)
                    .setExecutor(new AbsTask.Executor() {
                        @Override
                        public void execute() {
                            Test.doJob(200);
                        }
                    })
            );
        }
    };

    public static IModuleStart Module_d = new IModuleStart() {
        @Override
        public void buildTasks(ArrayList<AbsTask> tasks) {
            tasks.add(new ApplicationCPUTask(StartConstant.Module_d1)
                    .addDepend(StartConstant.Module_b5)
                    .setExecutor(new AbsTask.Executor() {
                        @Override
                        public void execute() {
                            Test.doJob(200);
                        }
                    })
            );
            tasks.add(new ApplicationCPUTask(StartConstant.Module_d2)
                    .setExecutor(new AbsTask.Executor() {
                        @Override
                        public void execute() {
                            Test.doJob(200);
                        }
                    })
            );
            tasks.add(new ApplicationCPUTask(StartConstant.Module_d3)
                    .setExecutor(new AbsTask.Executor() {
                        @Override
                        public void execute() {
                            Test.doJob(200);
                        }
                    })
            );
            tasks.add(new ApplicationCPUTask(StartConstant.Module_d4)
                    .setExecutor(new AbsTask.Executor() {
                        @Override
                        public void execute() {
                            Test.doJob(200);
                        }
                    })
            );
        }
    };


    public static IModuleStart Module_e = new IModuleStart() {
        @Override
        public void buildTasks(ArrayList<AbsTask> tasks) {
            tasks.add(new ApplicationCPUTask(StartConstant.Module_e1)
                    .addDepend(StartConstant.Module_c1)
                    .addDepend(StartConstant.Module_d2)
                    .setExecutor(new AbsTask.Executor() {
                        @Override
                        public void execute() {
                            Test.doJob(200);
                        }
                    })
            );
        }
    };


    public static IModuleStart Module_f = new IModuleStart() {
        @Override
        public void buildTasks(ArrayList<AbsTask> tasks) {
            tasks.add(new ApplicationCPUTask(StartConstant.Module_f1)
                    .addDepend(StartConstant.Module_d3)
                    .setExecutor(new AbsTask.Executor() {
                        @Override
                        public void execute() {
                            Test.doJob(200);
                        }
                    })
            );
            tasks.add(new ApplicationCPUTask(StartConstant.Module_f2)
                    .addDepend(StartConstant.Module_a3)
                    .addDepend(StartConstant.Module_34)
                    .setExecutor(new AbsTask.Executor() {
                        @Override
                        public void execute() {
                            Test.doJob(200);
                        }
                    })
            );

        }
    };

    public static IModuleStart Module_g = new IModuleStart() {
        @Override
        public void buildTasks(ArrayList<AbsTask> tasks) {
            tasks.add(new ApplicationCPUTask(StartConstant.Module_g1)
                    .addDepend(StartConstant.Module_15)
                    .setExecutor(new AbsTask.Executor() {
                        @Override
                        public void execute() {
                            Test.doJob(200);
                        }
                    })
            );
            tasks.add(new ApplicationCPUTask(StartConstant.Module_g2)
                    .addDepend(StartConstant.Module_a4)
                    .addDepend(StartConstant.Module_f1)
                    .addDepend(StartConstant.Module_61)
                    .addDepend(StartConstant.Module_12)
                    .setExecutor(new AbsTask.Executor() {
                        @Override
                        public void execute() {
                            Test.doJob(200);
                        }
                    })
            );

        }
    };


    public static IModuleStart Module_h = new IModuleStart() {
        @Override
        public void buildTasks(ArrayList<AbsTask> tasks) {
            tasks.add(new ApplicationCPUTask(StartConstant.Module_h1)
                    .setExecutor(new AbsTask.Executor() {
                        @Override
                        public void execute() {
                            Test.doJob(200);
                        }
                    })
            );
            tasks.add(new ApplicationCPUTask(StartConstant.Module_h2)
                    .setExecutor(new AbsTask.Executor() {
                        @Override
                        public void execute() {
                            Test.doJob(200);
                        }
                    })
            );

        }
    };

    public static IModuleStart Module_i = new IModuleStart() {
        @Override
        public void buildTasks(ArrayList<AbsTask> tasks) {
            tasks.add(new ApplicationCPUTask(StartConstant.Module_i1)
                    .addDepend(StartConstant.Module_a4)
                    .addDepend(StartConstant.Module_b1)
                    .addDepend(StartConstant.Module_c2)
                    .addDepend(StartConstant.Module_13)
                    .setExecutor(new AbsTask.Executor() {
                        @Override
                        public void execute() {
                            Test.doJob(200);
                        }
                    })
            );
            tasks.add(new ApplicationCPUTask(StartConstant.Module_i2)
                    .addDepend(StartConstant.Module_d1)
                    .setExecutor(new AbsTask.Executor() {
                        @Override
                        public void execute() {
                            Test.doJob(200);
                        }
                    })
            );

        }
    };

    public static IModuleStart Module_j = new IModuleStart() {
        @Override
        public void buildTasks(ArrayList<AbsTask> tasks) {
            tasks.add(new ApplicationCPUTask(StartConstant.Module_j1)
                    .addDepend(StartConstant.Module_i2)
                    .setExecutor(new AbsTask.Executor() {
                        @Override
                        public void execute() {
                            Test.doJob(200);
                        }
                    })
            );
        }
    };



}
