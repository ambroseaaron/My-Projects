/**
 * Consumer class given by the book Java How to Program
 */
import java.util.Random;
    public class Consumer implements Runnable {
        private static Random generator = new Random();
        private Buffer sharedLocation; // reference to shared object
        // constructor
        public Consumer( Buffer shared ) {
            sharedLocation = shared;
        } // end Consumer constructor
        // read sharedLocation's value four times and sum the values
        public void run() {
            int sum = 0;
            for ( int count = 1; count <= 10; count++ ) {
                // sleep 0 to 3 seconds, read value from buffer and add to sum
                try
                {
                    Thread.sleep( generator.nextInt( 3000 ) );
                    sum += sharedLocation.get();
                } // end try
                // if sleeping thread interrupted, print stack trace
                catch ( InterruptedException exception )
                {
                    exception.printStackTrace();
                } // end catch
            } // end for
        System.out.printf( "\n%s %d.\n%s\n", "Consumer read values totaling", sum, "Terminating Consumer." );
        } // end method run
    } // end class Consumer