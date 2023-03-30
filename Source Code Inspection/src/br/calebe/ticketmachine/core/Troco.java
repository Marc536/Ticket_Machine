package br.calebe.ticketmachine.core;

import java.util.Iterator;

/**
 * 
 * @author Calebe de Paula Bianchini
 */
class Troco {

    protected PapelMoeda[] papeisMoeda;

    public Troco(int valor) {
        papeisMoeda = new PapelMoeda[6];
        int count = 0;
        while (valor >= 100) {
            count++;
            valor -= 100;
        }
        papeisMoeda[5] = new PapelMoeda(100, count);
        count = 0;
        while (valor >= 50) {
            count++;
            valor -= 50;
        }
        papeisMoeda[4] = new PapelMoeda(50, count);
        count = 0;
        while (valor >= 20) {
            count++;
            valor -= 20;
        }
        papeisMoeda[3] = new PapelMoeda(20, count);
        count = 0;
        while (valor >= 10) {
            count++;
            valor -= 10;
        }
        papeisMoeda[2] = new PapelMoeda(10, count);
        count = 0;
        while (valor >= 5) {
            count++;
            valor -= 5;
        }
        papeisMoeda[1] = new PapelMoeda(5, count);
        count = 0;
        while (valor >= 2) {
            count++;
            valor -= 2;
        }
        papeisMoeda[0] = new PapelMoeda(2, count);
    }

    public Iterator<PapelMoeda> getIterator() {
        return new TrocoIterator(this);
    }

    class TrocoIterator implements Iterator<PapelMoeda> {

        protected Troco troco;
        protected int index;

        public TrocoIterator(Troco troco) {
            this.troco = troco;
            index = 5;
        }

        @Override
        public boolean hasNext() {
            for (int i = index; i >= 0; i--) {
                if (troco.papeisMoeda[i] != null) {
                    return true;
                }
            }
            return false;
        }

        @Override
        public PapelMoeda next() {
            PapelMoeda ret = null;
            for (int i = index; i >= 0 && ret == null; i--) {
                if (troco.papeisMoeda[i] != null) {
                    ret = troco.papeisMoeda[i];
                    troco.papeisMoeda[i] = null;
                    index = i - 1;
                }
            }
            return ret;
        }

        @Override
        public void remove() {
            next();
        }
    }
}
