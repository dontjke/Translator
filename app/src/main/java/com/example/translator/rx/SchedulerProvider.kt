package com.example.translator.rx

import io.reactivex.Scheduler


interface SchedulerProvider {
    fun ui(): Scheduler
    fun io(): Scheduler
}