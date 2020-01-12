import java.util.List;
import java.util.Random;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.SwingWorker;

public class PrimeCalculator extends SwingWorker<Integer, Integer>
{
	private final Random generator = new Random();
	private final JTextArea intermediateJTextArea;
	private final JButton getPrimesJButton;
	private final JButton cancelJButton;
	private final JLabel statusJLabel;
	private final boolean[] primes;
	
	public PrimeCalculator(int max, JTextArea intermediate, JLabel status, JButton getPrimes, JButton cancel)
	{
		intermediateJTextArea = intermediate;
		statusJLabel = status;
		getPrimesJButton = getPrimes;
		cancelJButton = cancel;
		primes = new boolean[max];
		
		for(int i=0; i<max; i++)
			primes[i] = true;
	}
	@Override
	protected Integer doInBackground() throws Exception {
		int count = 0;
		for (int i = 2; i < primes.length; i++) 
		{
			if(isCancelled()) //if task canceled before it is closed
				return count; //return found until cancellation
			else {
				setProgress(100*(i+1)/primes.length);
				
				try {
					Thread.sleep(generator.nextInt(5));
				} catch (InterruptedException e) {
					statusJLabel.setText("worker thread interrupted");
					return count;
				}
				
				if(primes[i])
				{
					publish(i); //make i available for display in prime list
					++count;
					
					for(int j = i+i; j<primes.length; j+=i)
						primes[j]=false;
				}
			}
		}
		return count;
	}
	
	@Override
	protected void process(List<Integer> publishedVals) 
	{
		for(int i=0 ; i<publishedVals.size(); i++)
			intermediateJTextArea.append(publishedVals.get(i) + "\n");
	}
	
	@Override
	protected void done() {
		getPrimesJButton.setEnabled(true);
		cancelJButton.setEnabled(false);
		
		int numPrimes;
		
		try {
			numPrimes = get();
		} catch (InterruptedException e) {
			statusJLabel.setText("Interrupted while waiting for results.");
			return;
		} catch (ExecutionException e) {
			statusJLabel.setText("Error performing computation.");
			return;
		} catch (CancellationException e) {
			statusJLabel.setText("Cancelled");
			return;
		}
		
		statusJLabel.setText("Found " + numPrimes + " primes.");
	}
}
