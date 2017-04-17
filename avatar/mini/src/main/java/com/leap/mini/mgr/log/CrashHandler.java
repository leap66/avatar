package com.leap.mini.mgr.log;

import android.content.Context;
import android.os.Process;

/**
 * 页面描述：崩溃时自动捕获崩溃信息
 * <p>
 * Created by ditclear on 2016/11/23.
 */
public final class CrashHandler implements Thread.UncaughtExceptionHandler {
  private static CrashHandler instance = new CrashHandler();
  private Logger logger = new Logger();

  private CrashHandler() {
  }

  public static CrashHandler getInstance() {
    return instance;
  }

  public void init(Context context) {
    Thread.setDefaultUncaughtExceptionHandler(this);
  }

  /**
   * . 当程序中有未被捕获的异常， 系统会自动调用 uncaughtException 方法
   *
   * @param thread
   *          出现未捕获异常的线程
   * @param throwable
   *          未捕获的异常
   */
  @Override
  public void uncaughtException(Thread thread, Throwable throwable) {
    logger.error(thread, throwable, true);
    Process.killProcess(Process.myPid());
  }
}