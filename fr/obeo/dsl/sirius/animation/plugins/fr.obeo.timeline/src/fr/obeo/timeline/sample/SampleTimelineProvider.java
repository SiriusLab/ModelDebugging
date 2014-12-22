/*******************************************************************************
 * Copyright (c) 2013 Obeo. All Rights Reserved.
 *
 * This software and the attached documentation are the exclusive ownership
 * of its authors and was conceded to the profit of Obeo SARL.
 * This software and the attached documentation are protected under the rights
 * of intellectual ownership, including the section "Titre II  Droits des auteurs (Articles L121-1 L123-12)"
 * By installing this software, you acknowledge being aware of this rights and
 * accept them, and as a consequence you must:
 * - be in possession of a valid license of use conceded by Obeo only.
 * - agree that you have read, understood, and will comply with the license terms and conditions.
 * - agree not to do anything that could conflict with intellectual ownership owned by Obeo or its beneficiaries
 * or the authors of this software
 *
 * Should you not agree with these terms, you must stop to use this software and give it back to its legitimate owner.
 *
 *******************************************************************************/
package fr.obeo.timeline.sample;

import fr.obeo.timeline.view.AbstractTimelineProvider;

/**
 * A sample {@link fr.obeo.timeline.view.ITimelineProvider ITimelineProvider}.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public class SampleTimelineProvider extends AbstractTimelineProvider {

	/**
	 * A sample branch.
	 *
	 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
	 */
	private class SampleBranch {
		/**
		 * Delay between notifications in miliseconds.
		 */
		private static final int DELAY_MS = 300;

		/**
		 * Sizes of choices.
		 */
		private final int[] possibleSteps;

		/**
		 * Selected possible step.
		 */
		private final int[] selected;

		/**
		 * The branch index.
		 */
		private final int branch;

		/**
		 * The size of the timeline.
		 */
		private int size;

		/**
		 * Constructor.
		 * 
		 * @param branch
		 *            the branch index
		 * @param size
		 *            the final size of the branch
		 */
		public SampleBranch(int branch, int size) {
			possibleSteps = new int[size];
			selected = new int[size];
			this.branch = branch;
			for (int i = 0; i < possibleSteps.length; ++i) {
				possibleSteps[i] = -1; // no possible steps
				selected[i] = -1; // no selection
			}
		}

		/**
		 * Tells if we can {@link SampleBranch#createChoice() create a choice}.
		 * 
		 * @return <code>true</code> if we can {@link SampleBranch#createChoice() create a choice},
		 *         <code>false</code> otherwise
		 */
		public boolean canCreateChoice() {
			return size < possibleSteps.length;
		}

		/**
		 * Creates choice.
		 */
		public void createChoice() {
			try {
				size++;
				notifyEndChanged(branch, size);
				final int index = size - 1;
				Thread.sleep(DELAY_MS);
				final int nbPossibleSteps = 1 + (int)(Math.random() * 5);
				possibleSteps[index] = nbPossibleSteps; // create possible steps
				notifyNumberOfPossibleStepsAtChanged(branch, index, nbPossibleSteps);
				Thread.sleep(DELAY_MS);
				selected[index] = (int)(Math.random() * nbPossibleSteps); // make a selection
				notifyIsSelectedChanged(branch, index, selected[index], true);
				Thread.sleep(DELAY_MS / 2);
				if (index - 1 >= 0) {
					final int[][] tmp = {{branch, selected[index] } };
					notifyFollowingsChanged(branch, index - 1, selected[index - 1], tmp);
					Thread.sleep(DELAY_MS / 2);
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	/**
	 * Branches.
	 */
	private final SampleBranch[] branches = new SampleBranch[2];

	/**
	 * Constructor.
	 */
	public SampleTimelineProvider() {
		for (int i = 0; i < branches.length; ++i) {
			branches[i] = new SampleBranch(i, 100);
		}
		new Thread(new Runnable() {

			@Override
			public void run() {
				boolean loop = true;
				while (loop) {
					loop = false;
					for (int i = 0; i < branches.length; ++i) {
						SampleBranch branch = branches[i];
						if (branch.canCreateChoice()) {
							branch.createChoice();
							loop = true;
						}
					}
				}
			}
		}).start();
	}

	@Override
	public int getNumberOfBranches() {
		return branches.length;
	}

	@Override
	public int getStart(int branch) {
		return 0;
	}

	@Override
	public String getTextAt(int branch) {
		return "branch " + branch;
	}

	@Override
	public int getEnd(int branch) {
		return branches[branch].size;
	}

	@Override
	public int getNumberOfPossibleStepsAt(int branch, int index) {
		return branches[branch].possibleSteps[index];
	}

	@Override
	public String getTextAt(int branch, int index) {
		return String.valueOf(index);
	}

	@Override
	public Object getAt(int branch, int index, int possibleStep) {
		return "something";
	}

	@Override
	public Object getAt(int branch, int index) {
		return "something";
	}

	@Override
	public int getSelectedPossibleStep(int branch, int index) {
		return branches[branch].selected[index];
	}

	@Override
	public String getTextAt(int branch, int index, int possibleStep) {
		return "possible step " + possibleStep;
	}

	@Override
	public int[][] getFollowings(int branch, int index, int possibleStep) {
		final int[][] res;

		if (index < branches[branch].size - 1 && possibleStep == branches[branch].selected[index]) {
			final int[][] tmp = {{branch, branches[branch].selected[index + 1] } };
			res = tmp;
		} else {
			final int[][] tmp = {};
			res = tmp;
		}

		return res;
	}

	@Override
	public int[][] getPrecedings(int branch, int index, int possibleStep) {
		final int[][] res;

		if (index > 0 && possibleStep == branches[branch].selected[index]) {
			final int[][] tmp = {{branch, branches[branch].selected[index - 1] } };
			res = tmp;
		} else {
			final int[][] tmp = {};
			res = tmp;
		}

		return res;
	}

}
