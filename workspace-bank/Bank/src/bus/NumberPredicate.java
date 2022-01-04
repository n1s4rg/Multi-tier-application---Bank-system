package bus;

import java.util.Comparator;

public class NumberPredicate implements Comparator<Account> {

	@Override
	public int compare(Account o1, Account o2) {
		// TODO Auto-generated method stub
		double aid = o1.getAccountNum();
        double a2id = o2.getAccountNum();

        if (aid == a2id)
            return 0;
        else if (aid > a2id)
            return -1;
        else
            return 1;
	}

}
