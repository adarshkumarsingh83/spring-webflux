# SPRING WEB FLUX EXCEPTION HANDLING 
---

### TO BUILD 
* mvvn clean package 

### TO RUN 
* mvn spring-boot:run 


### six methods provided to handle error

* onErrorReturn: 
```
return fallback value for entire stream (mono/flux). E.g. if there’s a flux of 10 elements, and error happens on element 3, then rest 4,5,6… won’t be executed, instead the fallback value will be considered.
```
* onErrorResume: 
```
return fallback value in terms on Mono/Flux for entire stream (mono/flux). E.g. if there’s a flux of 10 elements, and error happens on element 3, then rest 4,5,6… won’t be executed, instead the fallback value will be considered.
```
* onErrorContinue:     
```
consumes (error,data) and does NOT split it over. It considers the consumer for the error elements, and leave the downstream chain as it for good elements. E.g. if there’s a flux of 10 elements, and error happens on element 3, then all elements (1 to 10) except 3 will have normal execution, but element 3 will have a different execution as mentioned in the consumer of onErrorContinue
```
* doOnError: 
```
consumes error and spills it over. Stops execution for further elements in stream.
```

* onErrorMap: 
``` 
 cast one error into another. Stops execution for further elements in stream.
```

### All these five methods come in 3 variants,
* Simple: consider directly the expected argument
* With Exception: consider the expected argument, if the exception matches the exception class provided
* With Predicate: consider the expected argument, if the predicate yields true

### onErrorReturn: return fallback value
```   
  @Test
    public void onErrorReturnDirectly_Mono() {
        dataService.getMonoInteger()
                .map(i -> i / 0) // will produce ArithmeticException
                .onErrorReturn(4)
                .subscribe(num -> log.info("Number: {}", num));
    }

    @Test
    public void onErrorReturnIfArithmeticException_Mono() {
        dataService.getMonoInteger()
                .map(i -> i / 0) // will produce ArithmeticException
                .onErrorReturn(ArithmeticException.class, 4)
                .subscribe(num -> log.info("Number: {}", num));
    }

    @Test
    public void onErrorReturnIfPredicatePasses_Mono() {
        dataService.getMonoInteger()
                .map(i -> i / 0) // will produce ArithmeticException
                .onErrorReturn(error -> error instanceof ArithmeticException, 4)
                .subscribe(num -> log.info("Number: {}", num));
    }

    @Test
    public void onErrorReturnDirectly_Flex() {
        dataService.getFluxInteger()
                .map(i -> i / 0) // will produce ArithmeticException
                .onErrorReturn(4)
                .subscribe(num -> log.info("Number: {}", num));
    }

    @Test
    public void onErrorReturnIfArithmeticException_Flex() {
        dataService.getFluxInteger()
                .map(i -> i / 0) // will produce ArithmeticException
                .onErrorReturn(ArithmeticException.class, 4)
                .subscribe(num -> log.info("Number: {}", num));
    }

    @Test
    public void onErrorReturnIfPredicatePasses_Flex() {
        dataService.getFluxInteger()
                .map(i -> i / 0) // will produce ArithmeticException
                .onErrorReturn(error -> error instanceof ArithmeticException, 4)
                .subscribe(num -> log.info("Number: {}", num));
    }
```

### onErrorResume: return fallback value in terms on Mono/Flux
```   
 @Test
    public void onErrorResume_Mono() {
        dataService.getMonoInteger()
                .map(i -> i / 0) // will produce ArithmeticException
                .onErrorResume(error -> Mono.just(4))
                .subscribe(num -> log.info("Number: {}", num));
    }

    @Test
    public void onErrorResumeIfArithmeticException_Mono() {
        dataService.getMonoInteger()
                .map(i -> i / 0) // will produce ArithmeticException
                .onErrorResume(
                        ArithmeticException.class,
                        error -> Mono.just(4)
                )
                .subscribe(num -> log.info("Number: {}", num));
    }

    @Test
    public void onErrorResumeIfPredicatePasses_Mono() {
        dataService.getMonoInteger()
                .map(i -> i / 0) // will produce ArithmeticException
                .onErrorResume(
                        error -> error instanceof ArithmeticException,
                        error -> Mono.just(4)
                )
                .subscribe(num -> log.info("Number: {}", num));
    }


    @Test
    public void onErrorResume_Flux() {
        dataService.getFluxInteger()
                .map(i -> i / 0) // will produce ArithmeticException
                .onErrorResume(error -> Mono.just(4))
                .subscribe(num -> log.info("Number: {}", num));
    }

    @Test
    public void onErrorResumeIfArithmeticException_Flux() {
        dataService.getFluxInteger()
                .map(i -> i / 0) // will produce ArithmeticException
                .onErrorResume(
                        ArithmeticException.class,
                        error -> Mono.just(4)
                )
                .subscribe(num -> log.info("Number: {}", num));
    }

    @Test
    public void onErrorResumeIfPredicatePasses_Flux() {
        dataService.getFluxInteger()
                .map(i -> i / 0) // will produce ArithmeticException
                .onErrorResume(
                        error -> error instanceof ArithmeticException,
                        error -> Mono.just(4)
                )
                .subscribe(num -> log.info("Number: {}", num));
    }
```

### onErrorContinue: consumes (error,data) and does NOT split it over
```  
 @Test
    public void onErrorContinue_Mono() {
        dataService.getMonoInteger()
                .map(i -> i / 0) // will produce ArithmeticException
                .onErrorContinue((error, obj) -> log.info("Exception :[{}], DataObject :[{}]", error, obj))
                .subscribe(num -> log.info("Number: {}", num));
    }

    @Test
    public void onErrorContinueIfArithmeticException_Mono() {
        dataService.getMonoInteger()
                .map(i -> i / 0) // will produce ArithmeticException
                .onErrorContinue(
                        ArithmeticException.class,
                        (error, obj) -> log.info("Exception :[{}], DataObject :[{}]", error, obj)
                )
                .subscribe(num -> log.info("Number: {}", num));
    }

    @Test
    public void onErrorContinueIfPredicatePasses_Mono() {
        dataService.getMonoInteger()
                .map(i -> i / 0) // will produce ArithmeticException
                .onErrorContinue(
                        error -> error instanceof ArithmeticException,
                        (error, obj) -> log.info("Exception :[{}], DataObject :[{}]", error, obj)
                )
                .subscribe(num -> log.info("Number: {}", num));
    }


    @Test
    public void onErrorContinue_Flux() {
        dataService.getFluxInteger()
                .map(i -> i / 0) // will produce ArithmeticException
                .onErrorContinue((error, obj) -> log.info("Exception :[{}], DataObject :[{}]", error, obj))
                .subscribe(num -> log.info("Number: {}", num));
    }

    @Test
    public void onErrorContinueIfArithmeticException_Flux() {
        dataService.getFluxInteger()
                .map(i -> i / 0) // will produce ArithmeticException
                .onErrorContinue(
                        ArithmeticException.class,
                        (error, obj) -> log.info("Exception :[{}], DataObject :[{}]", error, obj)
                )
                .subscribe(num -> log.info("Number: {}", num));
    }

    @Test
    public void onErrorContinueIfPredicatePasses_Flux() {
        dataService.getFluxInteger()
                .map(i -> i / 0) // will produce ArithmeticException
                .onErrorContinue(
                        error -> error instanceof ArithmeticException,
                        (error, obj) -> log.info("Exception :[{}], DataObject :[{}]", error, obj)
                )
                .subscribe(num -> log.info("Number: {}", num));
    }
```


### doOnError: consumes error and spills it over
```   
 @Test
    public void doOnError_Mono() {
        dataService.getMonoInteger()
                .map(i -> i / 0) // will produce ArithmeticException
                .doOnError(error -> log.info("Exception Caught & Processing"))
                .subscribe(num -> log.info("Number: {}", num));
    }

    @Test
    public void doOnErrorIfArithmeticException_Mono() {
        dataService.getMonoInteger()
                .map(i -> i / 0) // will produce ArithmeticException
                .doOnError(
                        ArithmeticException.class,
                        error -> log.info("Exception Caught & Processing")
                )
                .subscribe(num -> log.info("Number: {}", num));
    }

    @Test
    public void doOnErrorIfPredicatePasses_Mono() {
        dataService.getMonoInteger()
                .map(i -> i / 0) // will produce ArithmeticException
                .doOnError(
                        error -> error instanceof ArithmeticException,
                        error -> log.info("Exception Caught & Processing")
                )
                .subscribe(num -> log.info("Number: {}", num));
    }


    @Test
    public void doOnError_Flux() {
        dataService.getFluxInteger()
                .map(i -> i / 0) // will produce ArithmeticException
                .doOnError(error -> log.info("Exception Caught & Processing"))
                .subscribe(num -> log.info("Number: {}", num));
    }

    @Test
    public void doOnErrorIfArithmeticException_Flux() {
        dataService.getFluxInteger()
                .map(i -> i / 0) // will produce ArithmeticException
                .doOnError(
                        ArithmeticException.class,
                        error -> log.info("Exception Caught & Processing")
                )
                .subscribe(num -> log.info("Number: {}", num));
    }

    @Test
    public void doOnErrorIfPredicatePasses_Flux() {
        dataService.getFluxInteger()
                .map(i -> i / 0) // will produce ArithmeticException
                .doOnError(
                        error -> error instanceof ArithmeticException,
                        error -> log.info("Exception Caught & Processing")
                )
                .subscribe(num -> log.info("Number: {}", num));
    }

```


### onErrorMap: cast one error into another
```` 
 @Test
    public void OnErrorMap_Mono() {
        dataService.getMonoInteger()
                .map(i -> i / 0) // will produce ArithmeticException
                .onErrorMap(error -> new RuntimeException("ApplicationCalculationException"))
                .subscribe(num -> log.info("Number: {}", num));
    }

    @Test
    public void OnErrorMapIfArithmeticException_Mono() {
        dataService.getMonoInteger()
                .map(i -> i / 0) // will produce ArithmeticException
                .onErrorMap(
                        ArithmeticException.class,
                        error -> new RuntimeException("ApplicationCalculationException")
                )
                .subscribe(num -> log.info("Number: {}", num));
    }

    @Test
    public void OnErrorMapIfPredicatePasses_Mono() {
        dataService.getMonoInteger()
                .map(i -> i / 0) // will produce ArithmeticException
                .onErrorMap(
                        error -> error instanceof ArithmeticException,
                        error -> new RuntimeException("ApplicationCalculationException")
                )
                .subscribe(num -> log.info("Number: {}", num));
    }

    @Test
    public void OnErrorMap_Flux() {
        dataService.getFluxInteger()
                .map(i -> i / 0) // will produce ArithmeticException
                .onErrorMap(error -> new RuntimeException("ApplicationCalculationException"))
                .subscribe(num -> log.info("Number: {}", num));
    }

    @Test
    public void OnErrorMapIfArithmeticException_Flux() {
        dataService.getFluxInteger()
                .map(i -> i / 0) // will produce ArithmeticException
                .onErrorMap(
                        ArithmeticException.class,
                        error -> new RuntimeException("ApplicationCalculationException")
                )
                .subscribe(num -> log.info("Number: {}", num));
    }

    @Test
    public void OnErrorMapIfPredicatePasses_Flux() {
        dataService.getFluxInteger()
                .map(i -> i / 0) // will produce ArithmeticException
                .onErrorMap(
                        error -> error instanceof ArithmeticException,
                        error -> new RuntimeException("ApplicationCalculationException")
                )
                .subscribe(num -> log.info("Number: {}", num));
    }
````
