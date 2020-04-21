package knickknacks.objects.blocks.functionals.guis;

import knickknacks.objects.blocks.functionals.containers.ContainerSaltingBarrel;
import knickknacks.objects.blocks.functionals.tileentities.TileEntitySaltingBarrel;
import knickknacks.utils.Reference;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GuiSaltingBarrel extends GuiContainer {

    private static final ResourceLocation SALTING_BARREL_GUI_TEXTURES = new ResourceLocation(Reference.MODID + ":textures/gui/container/salting_barrel.png");
    /** The player inventory bound to this GUI. */
    private final InventoryPlayer playerInventory;
    private final TileEntitySaltingBarrel tileBarrel;

    public GuiSaltingBarrel(InventoryPlayer playerInv, TileEntitySaltingBarrel barrelInv)
    {
        super(new ContainerSaltingBarrel(playerInv, barrelInv));
        this.playerInventory = playerInv;
        this.tileBarrel = barrelInv;
    }

    /**
     * Draws the screen and all the components in it.
     */
    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        this.drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);
    }

    /**
     * Draw the foreground layer for the GuiContainer (everything in front of the items)
     */
    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
    {
        String s = this.tileBarrel.getDisplayName().getUnformattedText();
        this.fontRenderer.drawString(s, this.xSize / 2 - this.fontRenderer.getStringWidth(s) / 2, 6, 4210752);
        this.fontRenderer.drawString(this.playerInventory.getDisplayName().getUnformattedText(), 8, this.ySize - 96 + 2, 4210752);
    }

    /**
     * Draws the background layer of this container (behind the items).
     */
    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
    {
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(SALTING_BARREL_GUI_TEXTURES);
        int i = (this.width - this.xSize) / 2;
        int j = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(i, j, 0, 0, this.xSize, this.ySize);

        if (TileEntitySaltingBarrel.isBurning(this.tileBarrel))
        {
            int k = this.getBurnLeftScaled(13);
            this.drawTexturedModalRect(i + 56, j + 36 + 12 - k, 176, 12 - k, 14, k + 1);
        }

        int l = this.getCookProgressScaled(24);
        this.drawTexturedModalRect(i + 79, j + 34, 176, 14, l + 1, 16);
    }

    private int getCookProgressScaled(int pixels)
    {
        int i = this.tileBarrel.getField(2);
        int j = this.tileBarrel.getField(3);
        return j != 0 && i != 0 ? i * pixels / j : 0;
    }

    private int getBurnLeftScaled(int pixels)
    {
        int i = this.tileBarrel.getField(1);

        if (i == 0)
        {
            i = 200;
        }

        return this.tileBarrel.getField(0) * pixels / i;
    }

}
