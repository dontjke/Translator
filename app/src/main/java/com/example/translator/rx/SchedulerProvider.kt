package com.example.translator.rx

import io.reactivex.rxjava3.core.Scheduler


interface SchedulerProvider {
    fun ui(): Scheduler
    fun io(): Scheduler
}