# JavaRMI-remote-semaphore
## Execute
- enter proper `build` directory
``` shell
cd ./build/classes/java/main
```
### Server
``` shell
java pl.szymanski.wiktor.Server
```
### Client
``` shell
java pl.szymanski.wiktor.Client PERMITS DELAY
```
    PERMITS - number <int> of permits to acquire
    DELAY - number <int> of miliseconds, that those permits will be held
