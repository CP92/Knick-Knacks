package knickknacks;

import org.apache.logging.log4j.Logger;

import knickknacks.proxy.CommonProxy;
import knickknacks.utils.Reference;
import knickknacks.utils.handlers.RegistryHandlers;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;

@Mod(modid = Reference.MODID, name = Reference.MODNAME, version = Reference.VERSION)
public class KnickKnacks
{
	@Instance
	public static KnickKnacks instance;
	
    public static final String MODID = "knickknacks";
    public static final String NAME = "Knick Knacks";
    public static final String VERSION = "0.01";

    private static Logger logger;

    @SidedProxy(clientSide = Reference.CLIENT, serverSide = Reference.SERVER)
    public static CommonProxy proxy;
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        logger = event.getModLog();
        RegistryHandlers.preInitRegistries(event);
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        RegistryHandlers.initRegistries(event);
    }
    
    @EventHandler
    public void postinit(FMLPostInitializationEvent event) {
    	
    }
    
}
