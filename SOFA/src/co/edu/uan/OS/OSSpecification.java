/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.edu.uan.OS;



/**
 *
 * @author DCA_3
 */
public class OSSpecification {
    
        
    long maxMemory,allocatedMemory,freeMemory;
    Runtime runtime = Runtime.getRuntime();

    public long getMaxMemory() {
        
        return maxMemory= runtime.maxMemory();
    }

    public long getAllocatedMemory() {
        return allocatedMemory= runtime.totalMemory();
    }

    public long getFreeMemory() {
        return freeMemory = runtime.freeMemory();
    }
                



    
}
