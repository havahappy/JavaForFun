package de.spring.webservices.rest.controller;

import static de.spring.webservices.rest.controller.adapters.RxJavaAdapter.deferredAdapter;

import java.util.Map;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.rx.RxResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import de.spring.webservices.domain.Car;
import de.spring.webservices.rest.business.service.RxJavaBusinessLogic;
import rx.Observable;
import rx.Single;

@RestController
@RequestMapping("/api/rxjava/cars/")
public class RxJavaCarController {
	private static final Logger LOGGER = LoggerFactory.getLogger(RxJavaCarController.class);
	private static final int PAGE = 2;
	private static final int PAGE_SIZE = 10;
    	
	private final RxJavaBusinessLogic rxJavaBusinessLogic;

	@Inject
    public RxJavaCarController(RxJavaBusinessLogic completableFutureBusinessLogic) {
		this.rxJavaBusinessLogic = completableFutureBusinessLogic;
	}

	@RequestMapping(produces = { MediaType.APPLICATION_JSON_UTF8_VALUE }, method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Single<Page<Car>> cars() {
		return rxJavaBusinessLogic.findAll(new PageRequest(PAGE, PAGE_SIZE)).toSingle();
    }

	@RequestMapping(path = "stream", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE }, method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public SseEmitter carsStream() {
		return RxResponse.sse(rxJavaBusinessLogic.findAllStream(new PageRequest(PAGE, PAGE_SIZE)));
    }

    @RequestMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Single<Car>> car(@RequestHeader(value = "MY_HEADER", required = false) String specialHeader,
    			@PathVariable("id") long id,
    			@RequestParam Map<String, String> params,
    			@RequestParam(value = "wheel", required = false) String[] wheelParams) {
    	    	
    	if (specialHeader != null) {
    		LOGGER.info("SPECIAL HEADER: " + specialHeader);
    	}
    	 
    	if (params.get("mirror") != null) {
    		LOGGER.info("MIRROR: " + params.get("mirror"));	
    	}
    	
    	if (params.get("window") != null) {
    		LOGGER.info("WINDOW: " + params.get("window"));
    	}
    	
    	if (wheelParams != null) {
    		for(String wheel : wheelParams) {
    			LOGGER.info(wheel);
    		}
    	}
    	
		// BE CAREFUL: I am returning Page object but when using io.reactivex.Observable (stream) instead of io.reactivex.Single (only one element)
		// if you want this code to work you will have to return DeferredResult<List<Car>> and you will have to call
		// the toList() method of Observable.
		// The toList() method is the only way I know for returning Observable (stream) perhaps in Spring 5.0.0 there will be something better.
		// Until then, this is the only way I know for using Observable with Spring.
    	Single<Car> car = rxJavaBusinessLogic.findById(id).toSingle();
    	
		return ResponseEntity.status(HttpStatus.OK).body(car);

    }
    
    @RequestMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
    		produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
    public DeferredResult<ResponseEntity<Car>> create(@RequestBody Car car) {
    	
    	return deferredAdapter(createAsync(car));
    }

    
    private Observable<ResponseEntity<Car>> createAsync(Car car) {
    	
		// BE CAREFUL: I am returning Page object but when using io.reactivex.Observable (stream) instead of io.reactivex.Single (only one element)
		// if you want this code to work you will have to return DeferredResult<List<Car>> and you will have to call
		// the toList() method of Observable.
		// The toList() method is the only way I know for returning Observable (stream) perhaps in Spring 5.0.0 there will be something better.
		// Until then, this is the only way I know for using Observable with Spring.
    	
    	return rxJavaBusinessLogic
    			.createThrowable(car)
    			.map(this::createResponseCar) /** .toList() **/;		
    }
    
    private ResponseEntity<Car> createResponseCar(Car car) {
		HttpHeaders headers = new HttpHeaders();
	    headers.add(HttpHeaders.LOCATION, "/api/rxjava/cars/" + car.getId());
	    return new ResponseEntity<>(car, headers, HttpStatus.CREATED);
    }
}
