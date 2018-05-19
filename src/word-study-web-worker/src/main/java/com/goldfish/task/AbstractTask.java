package com.goldfish.task;

import com.goldfish.common.CommonResult;
import com.goldfish.common.PageQuery;
import com.goldfish.common.log.LogTypeEnum;
import com.goldfish.constant.FinishState;
import com.goldfish.constant.State;
import com.goldfish.constant.TaskType;
import com.goldfish.domain.Task;
import com.goldfish.service.TaskService;
import com.goldfish.utils.DateUtils;

import javax.annotation.Resource;
import java.lang.reflect.AnnotatedType;
import java.util.Date;
import java.util.List;

/**
 * 抽象任务
 * Created by John on 2018/5/19 0019.
 */
public abstract class AbstractTask {
    @Resource(name="taskService")
    private TaskService taskService;
    private AnnotatedType taskType;

    /**
     * 处理任务
     */
    public void doTask() {
        Date begin = new Date();
        LogTypeEnum.DEFAULT.debug("定时任务[{}]，开始执行时间={}", getName(), DateUtils.format(begin));
        try {
            execute();
        } catch (Exception e) {
            LogTypeEnum.DEFAULT.error(e, "定时任务[{}]，执行失败", getName());
        } finally {
            LogTypeEnum.DEFAULT.debug("定时任务[{}]，执行结束，耗时={}ms", getName(),System.currentTimeMillis() - begin.getTime());
        }
    }

    /**
     * 批量执行任务
     */
    private void execute() {
        // 1.批量获取待执行任务
        PageQuery pageQuery = new PageQuery();
        pageQuery.setPageSize(getPageSize());
        pageQuery.addQueryParam("type", getTaskType().getType());
        pageQuery.addQueryParam("state", FinishState.NOT_COMPLETE.getState());
        CommonResult<List<Task>> result = taskService.getTaskByPage(pageQuery);
        if (!result.isSuccess()) {
            LogTypeEnum.DEFAULT.error("定时任务[{}]，批量获取待执行任务失败，msg={}",getName(),result.getMessage());
            return;
        }
        List<Task> tasks = result.getDefaultModel();

        if (tasks == null || tasks.isEmpty()) {
            LogTypeEnum.DEFAULT.info("定时任务[{}],没有任务需要执行");
            return;
        }

        //2.执行任务
        for (Task task : tasks) {
            if (execute(task)) {
                // 执行成功后更新任务状态
                task.setState(FinishState.COMPLETE.getState());
                CommonResult<Task> updateTaskResult = taskService.updateTask(task);
                if (!updateTaskResult.isSuccess()) {
                    LogTypeEnum.DEFAULT.error("定时任务[{}]，更新任务状态为完成失败，msg={}", getName(),updateTaskResult.getMessage());
                }

            }
        }
    }

    /**
     * 执行任务
     * @param task
     */
    protected abstract boolean execute(Task task);

    /**
     * 任务名称
     * @return
     */
    public abstract String getName();

    /**
     * 任务类型
     * @return
     */
    public abstract TaskType getTaskType();

    public int getPageSize() {
        return 100;
    }
}
