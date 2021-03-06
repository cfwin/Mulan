/*
 *    This program is free software; you can redistribute it and/or modify
 *    it under the terms of the GNU General Public License as published by
 *    the Free Software Foundation; either version 2 of the License, or
 *    (at your option) any later version.
 *
 *    This program is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *    GNU General Public License for more details.
 *
 *    You should have received a copy of the GNU General Public License
 *    along with this program; if not, write to the Free Software
 *    Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA.
 */

/*
 *    ExampleBasedAccuracy.java
 *    Copyright (C) 2009-2012 Aristotle University of Thessaloniki, Greece
 */
package mulan.evaluation.measure;

/**
 * Implementation of the example-based accuracy measure.
 *
 * @author Grigorios Tsoumakas
 * @version 2010.11.05
 */
public class ExampleBasedAccuracy extends ExampleBasedBipartitionMeasureBase
{

	private final double forgivenessRate;

	/**
	 * Constructs a new object
	 */
	public ExampleBasedAccuracy()
	{
		this(1.0);
	}

	/**
	 * Constructs a new object
	 *
	 * @param aForgivenessRate the forgiveness rate
	 */
	public ExampleBasedAccuracy(double aForgivenessRate)
	{
		this(ConfidenceLevelProvider.CONFIDENCE_LEVEL_ALL, aForgivenessRate);
	}

	public ExampleBasedAccuracy(ConfidenceLevel confLevel)
	{
		this(confLevel, 1.0);
	}

	public ExampleBasedAccuracy(ConfidenceLevel confLevel, double aForgivenessRate)
	{
		super(confLevel);
		forgivenessRate = aForgivenessRate;
	}

	public String getName()
	{
		return "Example-Based Accuracy" + confLevel.getName();
	}

	public double getIdealValue()
	{
		return 1;
	}

	@Override
	protected void updateBipartition(boolean[] bipartition, boolean[] truth)
	{
		double intersection = 0;
		double union = 0;
		for (int i = 0; i < truth.length; i++)
		{
			if (bipartition[i] && truth[i])
			{
				intersection++;
			}
			if (bipartition[i] || truth[i])
			{
				union++;
			}
		}

		if (union == 0)
		{
			sum += Math.pow(1, forgivenessRate);
		}
		else
		{
			sum += Math.pow(intersection / union, forgivenessRate);
		}
		count++;
	}
}