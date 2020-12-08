package code;

import java.util.Comparator;

public class CandidatoComparator implements Comparator<Candidato> {

	@Override
	public int compare(Candidato arg0, Candidato arg1) {
		if(arg0.getNota() != arg1.getNota()) {
			return arg1.getNota() - arg0.getNota();
		}
		return arg1.getIdade() - arg0.getIdade();
	}

}
