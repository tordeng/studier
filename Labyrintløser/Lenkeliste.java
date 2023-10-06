import java.util.Iterator;

class Lenkeliste<T> implements Liste<T> {
	protected Node head, tail;
	protected int size;

	protected class Node {
		T element;
		Node neste;
		Node forrige;
		
		Node(T elm) { element = elm; }
	}
	
	protected class LenkelisteIterator implements Iterator<T> {
		Node nesteNodeSomSkalUt = head.neste;
		
		public boolean hasNext() {
			return nesteNodeSomSkalUt != tail;
		}
		
		public T next() {
			T data = nesteNodeSomSkalUt.element;
			nesteNodeSomSkalUt = nesteNodeSomSkalUt.neste;
			return data;
		}
	}
	
	public Lenkeliste() {
		head = new Node(null);
		tail = new Node(null);
		head.neste = tail;
		tail.forrige = head;
	}
	
	public void leggTil(int pos, T x) {
		if (pos < 0 || pos > size - 1)
			throw new UgyldigListeIndeks(pos);
		// if (pos < 0)
			// throw new UgyldigListeIndeks(pos);
		// if (pos > size)
			// throw new UgyldigListeIndeks(pos);
		
		Node n = new Node(x);
		Node p = head.neste;
		
		for (int i = 0; i < pos; i++) {
			p = p.neste;
		}
		
		n.neste = p;
		p.forrige.neste = n;
		
		n.forrige = p.forrige;
		p.forrige = n;
		
		p.element = x;
		
		size++;
	}
	
	public void leggTil(T x) {
		Node n = new Node(x);
		
		n.neste = tail;
		n.forrige = tail.forrige;
		
		tail.forrige.neste = n;
		tail.forrige = n;
		
		size++;
	}
	
	//tolker som at sett bare kan erstatte, og ikke legge in uten å ta ut.
	public void sett(int pos, T x) {
		if (pos < 0 || pos > size - 1)
			throw new UgyldigListeIndeks(pos);
		// if (pos < 0)
			// throw new UgyldigListeIndeks(pos);
		// if (pos > size-1)
			// throw new UgyldigListeIndeks(pos);
		// if (size == 0)
			// throw new UgyldigListeIndeks(pos);
		
		Node n = new Node(x);
		Node p = head.neste;
		
		for (int i = 0; i < pos; i++) {
			p = p.neste;
		}
		
		p.element = x;
	}
	
	public T fjern(int pos) {
		if (pos < 0 || pos > size - 1)
			throw new UgyldigListeIndeks(pos);
		// if (pos < 0)
			// throw new UgyldigListeIndeks(pos);
		// if (pos > size-1)
			// throw new UgyldigListeIndeks(pos);
		// if (size == 0)
			// throw new UgyldigListeIndeks(pos);
		
		Node n = head;
		
		for (int i = 0; i < pos; i++) {
			n = n.neste;
		}
		T svar = n.neste.element;

		Node p = n.neste;
		n.neste = p.neste;
		n.neste.forrige = n;
		
		size--;
		return svar;
	}
	
	public T fjern() {
		if (size == 0)
			throw new UgyldigListeIndeks(0);
		
		Node n = head;
		T svar = n.neste.element;
		Node p = n.neste;
		n.neste = p.neste;
		size--;
		return svar;
	}
	
	public int stoerrelse() {
		return size;
	}
	
	public T hent(int pos) {
		if (pos < 0 || pos > size - 1)
			throw new UgyldigListeIndeks(pos);
		// if (pos < 0)
			// throw new UgyldigListeIndeks(pos);
		// if (pos > size-1)
			// throw new UgyldigListeIndeks(pos);
		
		Node n = head.neste;
		
		for (int i = 0; i < pos; i++) {
			n = n.neste;
		}
		return n.element;
	}
	
	// public T hentAlle() {
		// Node n = head.neste;
		
		// for (int i = 0; i < size-1; i++) {
			// return n.element; 
		// }
	// }
	
	public Iterator<T> iterator() {
		return new LenkelisteIterator();
	}
	
	public String toString() {
		String res = "";
		for (T t : this)
			res += t + ", ";
		return res.substring(0, res.length() - 2);
	}
}