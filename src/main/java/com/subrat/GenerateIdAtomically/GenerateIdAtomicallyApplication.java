package com.subrat.GenerateIdAtomically;

import com.subrat.GenerateIdAtomically.approachThree.IDGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class GenerateIdAtomicallyApplication {

	public static void main(String[] args) {
        //testApproachOne();
//        testApproachTwo(100000);
		testApproachThree(100000);
	}
	public static void  testApproachOne() {
		IDGenerator idGenerator = new IDGenerator(100);
		System.out.println("ApproachOne Testing : ");
		ExecutorService executorService = Executors.newFixedThreadPool(10);

		List<Future<Integer>> ids = new ArrayList<>();

		for (int i = 0; i<1000000; i++) {
			ids.add(executorService.submit(idGenerator::getId));
		}

		for(int i = 0; i<1000000; i++) {
			try {
				System.out.println(ids.get(i).get());
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			} catch (ExecutionException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
		}

		System.out.println("ApproachOne Testing : done");
	}

	public static void  testApproachTwo(int n) {
		long startTime = System.currentTimeMillis();
		IDGenerator idGenerator = new IDGenerator(1);
		System.out.println("ApproachTwo Testing for n : " + n);

		ExecutorService executorService = Executors.newFixedThreadPool(1000);
		List<Future<Integer>> ids = new ArrayList<>();

		for (int i = 0; i<n; i++) {
			ids.add(executorService.submit(idGenerator::getId));
		}

		for(int i = 0; i<n; i++) {
			try {
				System.out.println(ids.get(i).get());
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			} catch (ExecutionException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
		}
		long timeTaken = System.currentTimeMillis()  - startTime;
		System.out.println("ApproachTwo Testing time taken : " + timeTaken + " ms");
	}

	public static void  testApproachThree(int n) {
		long startTime = System.currentTimeMillis();
		IDGenerator idGenerator = new IDGenerator(100);
		System.out.println("ApproachThree Testing for n : " + n);

		ExecutorService executorService = Executors.newFixedThreadPool(1000);
		List<Future<Integer>> ids = new ArrayList<>();

		for (int i = 0; i<n; i++) {
			ids.add(executorService.submit(idGenerator::getId));
		}

		for(int i = 0; i<n; i++) {
			try {
				System.out.println(ids.get(i).get());
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			} catch (ExecutionException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
		}
		long timeTaken = System.currentTimeMillis()  - startTime;
		System.out.println("ApproachThree Testing time taken : " + timeTaken + " ms");
	}

}
