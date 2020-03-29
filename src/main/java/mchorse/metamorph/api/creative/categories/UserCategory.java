package mchorse.metamorph.api.creative.categories;

import mchorse.metamorph.api.creative.sections.MorphSection;
import mchorse.metamorph.api.creative.sections.UserSection;
import mchorse.metamorph.api.morphs.AbstractMorph;

public class UserCategory extends MorphCategory
{
	public UserCategory(MorphSection parent, String title)
	{
		super(parent, title);
	}

	@Override
	public boolean isHidden()
	{
		return false;
	}

	@Override
	public void add(AbstractMorph morph)
	{
		super.add(morph);

		if (this.parent instanceof UserSection)
		{
			((UserSection) this.parent).save();
		}
	}

	@Override
	public boolean remove(AbstractMorph morph)
	{
		boolean result = super.remove(morph);

		if (result && this.parent instanceof UserSection)
		{
			((UserSection) this.parent).save();
		}

		return result;
	}

	@Override
	public void edit(AbstractMorph morph)
	{
		int index = this.morphs.indexOf(morph);

		if (index >= 0 && this.parent instanceof UserSection)
		{
			((UserSection) this.parent).save();
		}
	}
}