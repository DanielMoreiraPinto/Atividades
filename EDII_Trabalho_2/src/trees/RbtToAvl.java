package trees;

public class RbtToAvl<Key extends Comparable<Key>, Value> {
	
	@SuppressWarnings("unchecked")
	public AVLTree<Key, Value> convert(RBTree<Key, Value> rbt) {
		
		// Chama o iterador da árvore rubro-negra
		RBTree<Key, Value>.Iterator it =  rbt.Iterator();
		AVLTree<Key, Value> avlt = new AVLTree<Key, Value>();
		
		// Itera pela árvore e insere os elementos na árvore balanceada
		while(it.hasNext()) {
			Object[] aux = it.next();
			avlt.put((Key)aux[0], (Value)aux[1]);
		}
    	
    	return avlt;
	}
	
}
