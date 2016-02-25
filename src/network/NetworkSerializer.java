package network;

import com.jme3.network.serializing.Serializer;
import network.message.IdentificationMessage;
import network.message.world.InitWorldMessage;

/**
 * Used to serialize all Messages which will be used for server-client communication.
 *
 * @author Marco Klein
 */
public class NetworkSerializer {
    public static void registerClasses() {
        Serializer.registerClass(IdentificationMessage.class);
        
        Serializer.registerClass(InitWorldMessage.class);
    }
    
}
