package com.iomete.sdk.spark.job.models;

import com.iomete.sdk.models.JsonModel;

public class SparkJobScheduleResponse extends JsonModel<SparkJobScheduleResponse> {
    private String cron;
    private ConcurrencyState concurrency;
    private SparkJobStatus status;

    public SparkJobScheduleResponse() {}

    public String getCron() {
        return cron;
    }

    public ConcurrencyState getConcurrency() {
        return concurrency;
    }

    public SparkJobStatus getStatus() {
        return status;
    }

    public void setCron(String cron) {
        this.cron = cron;
    }

    public void setConcurrency(ConcurrencyState concurrency) {
        this.concurrency = concurrency;
    }

    public void setStatus(SparkJobStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return this.toJson(true);
    }
}
