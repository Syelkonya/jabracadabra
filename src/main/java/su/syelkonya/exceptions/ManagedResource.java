package su.syelkonya.exceptions;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ManagedResource implements AutoCloseable{

    public ManagedResource() {
       log.info("open " + this.getClass().getSimpleName());
    }

    @Override
    public void close() throws Exception {
        log.info("close " + this.getClass().getSimpleName());
    }
}
