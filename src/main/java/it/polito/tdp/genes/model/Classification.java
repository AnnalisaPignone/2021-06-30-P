package it.polito.tdp.genes.model;

public class Classification {

	String localizzazione; 
	
	public Classification(String localizzazione) {
		super();
		this.localizzazione = localizzazione;
	}

	public String getLocalizzazione() {
		return localizzazione;
	}

	public void setLocalizzazione(String localizzazione) {
		this.localizzazione = localizzazione;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((localizzazione == null) ? 0 : localizzazione.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Classification other = (Classification) obj;
		if (localizzazione == null) {
			if (other.localizzazione != null)
				return false;
		} else if (!localizzazione.equals(other.localizzazione))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return localizzazione;
	}
	
	

	
	
	
	
	
	
	
}
