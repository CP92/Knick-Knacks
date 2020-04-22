package knickknacks.particles;

import net.minecraft.client.particle.ParticleRedstone;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class SaltingBarrelParticles extends ParticleRedstone
{

	public SaltingBarrelParticles(World worldIn, double xCoordIn, double yCoordIn, double zCoordIn, float f, float p_i46349_8_, float p_i46349_9_, float p_i46349_10_)
	{
		super(worldIn, xCoordIn, yCoordIn, zCoordIn, 1.0F, p_i46349_8_, p_i46349_9_, p_i46349_10_);
	}

	@Override
	public boolean shouldDisableDepth()
	{
		return false;
	}

	public static SaltingBarrelParticles CreateParticle(World worldIn, double xCoordIn, double yCoordIn, double zCoordIn, float f, float p_i46349_8_, float p_i46349_9_, float p_i46349_10_)
	{
		SaltingBarrelParticles particle = new SaltingBarrelParticles(worldIn, xCoordIn, yCoordIn, zCoordIn, 1.0F, p_i46349_8_, p_i46349_9_, p_i46349_10_);
		((ParticleRedstone) particle).setParticleTextureIndex(0);
		float f1 = worldIn.rand.nextFloat() * 0.05F + 0.95F;
		particle.setRBGColorF(255F * f1, 255F * f1, 255F * f1);
		return particle;
	}

}