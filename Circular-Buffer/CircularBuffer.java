/**
 * CircularBuffer uses a 3 element array and has a producer write values
 * and has the consumer consume those values
 * Uses locks and conditions to monitor the buffer
 */
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;

public class CircularBuffer implements Buffer {
    // Lock to control synchronization with this buffer
    private Lock accessLock = new ReentrantLock();

    // conditions to control reading and writing
    private Condition canWrite = accessLock.newCondition();
    private Condition canRead = accessLock.newCondition();

    private int[] buffer = { 0, 0, 0};

    private int occupiedBuffers = 0; // count number of buffers used
    private int writeIndex = 0; // index to write next value
    private int readIndex = 0; // index to read next value

    /**
     * Places the value into the buffer when there is room
     * @param value number to be placed in the buffer
     */
    public void set( int value )
    {
        accessLock.lock(); // lock this object
        // output thread information and buffer information, then wait
        try
        {
            // while no empty locations, place thread in waiting state
            while ( occupiedBuffers == buffer.length )
            {
                System.out.printf( "All buffers full. Producer waits.\n" );
                canWrite.await();// await until a buffer element is free
            } // end while

            buffer[ writeIndex ] = value; // set new buffer value

            // update circular write index
            writeIndex = ( writeIndex + 1 ) % buffer.length;

            occupiedBuffers++; // one more buffer element is full
            displayState( "Producer writes " + value );
            canRead.signal(); // signal threads waiting to read from buffer
        } // end try
        catch ( InterruptedException exception )
        {
            exception.printStackTrace();
        } // end catch
        finally
        {
            accessLock.unlock(); // unlock this object
        } // end finally
    } // end method set

    /**
     * Method to get the value from the buffer
     * @return readValue value in the buffer
     */
    public int get()
    {
        int readValue = 0; // initialize value read from buffer
        accessLock.lock(); // lock this object

        // wait until buffer has data, then read value
        try
        {
            // while no data to read, place thread in waiting state
            while ( occupiedBuffers == 0 )
            {
                System.out.printf( "All buffers empty. Consumer waits.\n" );
                canRead.await(); // await until a buffer element is filled
            } // end while

            readValue = buffer[ readIndex ]; // read value from buffer

            // update circular read index
            readIndex = ( readIndex + 1 ) % buffer.length;
            occupiedBuffers--; // one more buffer element is empty
            displayState( "Consumer reads " + readValue );
            canWrite.signal(); // signal threads waiting to write to buffer
        } // end try
        // if waiting thread interrupted, print stack trace
        catch ( InterruptedException exception )
        {
            exception.printStackTrace();
        } // end catch
        finally
        {
            accessLock.unlock(); // unlock this object
        } // end finally

        return readValue;
    } // end method get

    /**
     * Method that neatly displays the buffer values and each
     * location of the read and write indexes
     * @param operation buffer is either written to or read from
     */
    public void displayState( String operation )
    {
        // output operation and number of occupied buffers
        System.out.printf( "%s%s%d)\n%s", operation,
                " (buffers occupied: ", occupiedBuffers, "buffers:  " );

        for ( int value : buffer )
            System.out.printf( " %2d  ", value ); // output values in buffer
        System.out.print( "\n         " );
        for ( int i = 0; i < buffer.length; i++ )
            System.out.print( "---- " );

        System.out.print( "\n         " );
        for ( int i = 0; i < buffer.length; i++ )
        {
            if ( i == writeIndex && i == readIndex )
                System.out.print( " WR" ); // both write and read index
            else if ( i == writeIndex )
                System.out.print( " W   " ); // just write index
            else if ( i == readIndex )
                System.out.print( "  R  " ); // just read index
            else
                System.out.print( "     " ); // neither index
        } // end for

        System.out.println( "\n" );
    } // end method displayState
} // end class CircularBuffer