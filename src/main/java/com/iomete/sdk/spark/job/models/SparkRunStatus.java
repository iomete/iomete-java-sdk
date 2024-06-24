package com.iomete.sdk.spark.job.models;

public enum SparkRunStatus {
    ENQUEUED,
    SUBMITTED,
    SUBMISSION_FAILED,

    RUNNING,
    PENDING_RERUN,

    SUCCEEDING,
    COMPLETED,

    FAILING,
    FAILED,

    INVALIDATING,

    ABORTED,

    UNKNOWN,
}
