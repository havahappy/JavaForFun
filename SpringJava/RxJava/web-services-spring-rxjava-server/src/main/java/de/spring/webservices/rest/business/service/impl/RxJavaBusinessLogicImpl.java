package de.spring.webservices.rest.business.service.impl;

import java.io.IOException;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import de.spring.webservices.domain.Car;
import de.spring.webservices.rest.business.service.AwesomeBusinessLogic;
import de.spring.webservices.rest.business.service.RxJavaBusinessLogic;
import rx.Observable;
import rx.Observable.OnSubscribe;
import rx.Subscriber;
import rx.exceptions.Exceptions;
import rx.schedulers.Schedulers;


@Service("rxJavaBusinessLogic")
public class RxJavaBusinessLogicImpl implements RxJavaBusinessLogic {
	private static final Logger LOGGER = LoggerFactory.getLogger(RxJavaBusinessLogicImpl.class);

    private final AwesomeBusinessLogic awesomeBusinessLogic;
    
    @Inject
	public RxJavaBusinessLogicImpl(AwesomeBusinessLogic awesomeBusinessLogic) {
		this.awesomeBusinessLogic = awesomeBusinessLogic;
	}

	@Override
	public Observable<Page<Car>> findAll(Pageable pageRequest) {
    	return Observable.create(new OnSubscribe<Page<Car>>() {
			@Override
			public void call(Subscriber<? super Page<Car>> observer) {
				observer.onNext( awesomeBusinessLogic.findAll(pageRequest));
			}
		}).subscribeOn(Schedulers.io());
	}
	
	@Override
	public Observable<Page<Car>> findAllStream(Pageable pageRequest) {
    	return Observable.create(new OnSubscribe<Page<Car>>() {
			@Override
			public void call(Subscriber<? super Page<Car>> observer) {
				observer.onNext( awesomeBusinessLogic.findAll(pageRequest));
			}
		}).subscribeOn(Schedulers.io());
	}

	@Override
	public Observable<Car> findById(long id) {
    	return Observable.create((Subscriber<? super Car> observer) ->
    				observer.onNext( awesomeBusinessLogic.findById(id)))
    			.subscribeOn(Schedulers.io());
	}

	@Override
	public Observable<Car> create(Car car) {	
		return Observable.create((Subscriber<? super Car> observer) ->
					observer.onNext(awesomeBusinessLogic.create(car)))
				.subscribeOn(Schedulers.io());
	}
	
	@Override
	public Observable<Car> createThrowable(Car car) {	
		return Observable.create((Subscriber<? super Car> observer) -> {

				try {
					observer.onNext(awesomeBusinessLogic.createThrowable(car));
				} catch (IOException ex) {
					// I could use this implementation. Instead, I will wrap my exception because
					// that is what you would be doing if you were using any other method from RxJava (like map() for example)
					// observer.onError(ex);
					
					LOGGER.error("createThrowable error: ", ex);
					
					Exceptions.propagate(ex);
				}
				
				// No idea when to use this stuff :(
				// observer.onCompleted();

		}).subscribeOn(Schedulers.io());
	}
}
