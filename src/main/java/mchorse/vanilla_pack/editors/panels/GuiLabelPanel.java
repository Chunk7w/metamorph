package mchorse.vanilla_pack.editors.panels;

import mchorse.mclib.client.gui.framework.elements.GuiScrollElement;
import mchorse.mclib.client.gui.framework.elements.buttons.GuiToggleElement;
import mchorse.mclib.client.gui.framework.elements.input.GuiColorElement;
import mchorse.mclib.client.gui.framework.elements.input.GuiTextElement;
import mchorse.mclib.client.gui.framework.elements.input.GuiTrackpadElement;
import mchorse.mclib.client.gui.utils.Elements;
import mchorse.mclib.client.gui.utils.keys.IKey;
import mchorse.metamorph.client.gui.editor.GuiMorphPanel;
import mchorse.vanilla_pack.editors.GuiLabelMorph;
import mchorse.vanilla_pack.morphs.LabelMorph;
import net.minecraft.client.Minecraft;

public class GuiLabelPanel extends GuiMorphPanel<LabelMorph, GuiLabelMorph>
{
    public GuiScrollElement element;

    public GuiTextElement label;
    public GuiTrackpadElement max;
    public GuiTrackpadElement anchorX;
    public GuiTrackpadElement anchorY;
    public GuiColorElement color;
    public GuiToggleElement lighting;

    public GuiToggleElement shadow;
    public GuiTrackpadElement shadowX;
    public GuiTrackpadElement shadowY;
    public GuiColorElement shadowColor;

    public GuiColorElement background;
    public GuiTrackpadElement offset;

    public GuiToggleElement billboard;

    public GuiLabelPanel(Minecraft mc, GuiLabelMorph editor)
    {
        super(mc, editor);

        this.label = new GuiTextElement(mc, 10000, (label) -> this.morph.label = label);
        this.max = new GuiTrackpadElement(mc, (value) -> this.morph.max = value.intValue());
        this.max.limit(-1, Integer.MAX_VALUE, true).increment(10);
        this.anchorX = new GuiTrackpadElement(mc, (value) -> this.morph.anchorX = value.floatValue());
        this.anchorX.values(0.01F);
        this.anchorY = new GuiTrackpadElement(mc, (value) -> this.morph.anchorY = value.floatValue());
        this.anchorY.values(0.01F);
        this.color = new GuiColorElement(mc, (value) -> this.morph.color = value);
        this.lighting = new GuiToggleElement(mc, IKey.lang("metamorph.gui.label.lighting"), (button) -> this.morph.lighting = button.isToggled());
        this.lighting.tooltip(IKey.lang("metamorph.gui.label.lighting_tooltip"));

        this.shadow = new GuiToggleElement(mc, IKey.lang("metamorph.gui.label.shadow"), (button) -> this.morph.shadow = button.isToggled());
        this.shadowX = new GuiTrackpadElement(mc, (value) -> this.morph.shadowX = value.floatValue());
        this.shadowX.limit(-100, 100).values(0.1F, 0.01F, 0.5F).increment(0.1F);
        this.shadowY = new GuiTrackpadElement(mc, (value) -> this.morph.shadowY = value.floatValue());
        this.shadowY.limit(-100, 100).values(0.1F, 0.01F, 0.5F).increment(0.1F);
        this.shadowColor = new GuiColorElement(mc, (value) -> this.morph.shadowColor = value);

        this.background = new GuiColorElement(mc, (value) -> this.morph.background = value);
        this.background.picker.editAlpha();
        this.offset = new GuiTrackpadElement(mc, (value) -> this.morph.offset = value.floatValue());

        this.billboard = new GuiToggleElement(mc, IKey.lang("metamorph.gui.label.billboard"), (b) -> this.morph.billboard = b.isToggled());

        this.element = new GuiScrollElement(mc);
        this.element.scroll.opposite = true;
        this.element.flex().relative(this).w(120).h(1F).column(5).vertical().stretch().scroll().height(20).padding(10);

        this.element.add(Elements.label(IKey.lang("metamorph.gui.label.label")).marginTop(8), this.label);
        this.element.add(Elements.label(IKey.lang("metamorph.gui.label.max_width")).marginTop(8), this.max);
        this.element.add(Elements.label(IKey.lang("metamorph.gui.label.anchor")).marginTop(8), this.anchorX, this.anchorY);
        this.element.add(Elements.label(IKey.lang("metamorph.gui.label.color")).marginTop(8), this.color, this.lighting);

        this.element.add(this.shadow);
        this.element.add(Elements.label(IKey.lang("metamorph.gui.label.shadow_offset")).marginTop(8), this.shadowX, this.shadowY);
        this.element.add(Elements.label(IKey.lang("metamorph.gui.label.shadow_color")).marginTop(8), this.shadowColor);

        this.element.add(Elements.label(IKey.lang("metamorph.gui.label.background")).marginTop(8), this.background, this.offset);
        this.element.add(this.billboard.marginTop(8));

        this.add(this.element);
    }

    @Override
    public void fillData(LabelMorph morph)
    {
        super.fillData(morph);

        this.label.setText(morph.label);
        this.max.setValue(morph.max);
        this.anchorX.setValue(morph.anchorX);
        this.anchorY.setValue(morph.anchorY);
        this.color.picker.setColor(morph.color);
        this.lighting.toggled(morph.lighting);

        this.shadow.toggled(morph.shadow);
        this.shadowX.setValue(morph.shadowX);
        this.shadowY.setValue(morph.shadowY);
        this.shadowColor.picker.setColor(morph.shadowColor);

        this.background.picker.setColor(morph.background);
        this.offset.setValue(morph.offset);
        this.billboard.toggled(morph.billboard);
    }
}