package com.learn.essentials.schedulers;

import io.reactivex.Scheduler;
import io.reactivex.functions.Action;
import io.reactivex.schedulers.Schedulers;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class InsideScheduler {

	void schedule(Scheduler scheduler, int numberOfSubTasks, boolean isOnTheSameWorker){
		List<Integer> list = new ArrayList<>(0);
		AtomicInteger current = new AtomicInteger(0);
		Random random = new Random();

		Scheduler.Worker worker = scheduler.createWorker();
		Runnable addWork = () -> {
			synchronized (Object.class){
				System.out.println(" Add : " +  currentThreadName()
					+ " " + current.get());
				list.add(random.nextInt(current.get()));
				System.out.println(" End add: " +  currentThreadName()
						+ " " + current.get());
//				System.out.println("End of add work");
			}
		};

		Runnable removeWork = () ->{
			synchronized (Object.class){
				System.out.println(" Remove : " +  currentThreadName());
				list.remove(0);
				System.out.println(" End remove: " +  currentThreadName());
			}
		};

		Runnable work = () ->{
			System.out.println(currentThreadName());
			for(int index = 1; index <= numberOfSubTasks; index ++){
				current.set(index);
				System.out.println("Begin add!");
				if(isOnTheSameWorker){
					worker.schedule(addWork);
				} else{
					scheduler.createWorker().schedule(addWork);
				}
				System.out.println("End add!");
			}
			while (!list.isEmpty()){
				System.out.println("Begin remove!");
				if(isOnTheSameWorker){
					worker.schedule(removeWork);
				} else {
					scheduler.createWorker().schedule(removeWork);
				}
				System.out.println("End remove !");
			}
			System.out.println("End of work!");
		};
		worker.schedule(work);
	}

	private static String currentThreadName(){
		return Thread.currentThread().getName();
	}

	private void testTrampoline(){
		Scheduler scheduler = Schedulers.trampoline();
		/*
		* true means we only use one Worker, so all the work will be executed sequential.
		* */
		schedule(scheduler, 3, false);
	}

	private void testNewThread(){
		Scheduler scheduler = Schedulers.newThread();
		schedule(scheduler, 2, false);
	}

	private void confirmNewThread(){
		final Runnable runnable = () ->  {
			System.out.println(Thread.currentThread().getName());
		};
		Scheduler scheduler = Schedulers.newThread();
		for (int count = 0; count < 5; count ++){
			/*
			* It will create a new thread for every NewThreadWorker.
			* */
			scheduler.createWorker().schedule(runnable);
		}
	}

	public static void main(String[] args) {
		new InsideScheduler().confirmNewThread();
	}

}
