package it.polito.tdp.model;

import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.dao.EsameDAO;

public class Model {
	private EsameDAO dao;
	private List<Esame> tuttiEsami;
	private List<Esame> ottima;
	double mediaBest=0;
	public Model() {
		dao=new EsameDAO();
		tuttiEsami= new LinkedList<Esame>(dao.getTuttiEsami());
		
	}
	

	public List<Esame> calcolaSottoinsiemeEsami(int numeroCrediti) {
		
		ottima= new LinkedList<Esame>();
		List<Esame> parziale= new LinkedList<Esame>();
		cerca(parziale, numeroCrediti ,0);
		
		return ottima;
	}
	
	private void cerca(List<Esame> parziale, int crediti, int L) {
		
		if(parziale.size()>=1) {
			
		
		if(getCrediti(parziale)==crediti) {
			if(getMedia(parziale)>mediaBest){
				this.ottima= new LinkedList<Esame>(parziale);
				mediaBest=getMedia(parziale);
				System.out.println(mediaBest+" \n");
			}
		} 
		if(getCrediti(parziale)>crediti) return;
		
		}
	/*	for(Esame e: this.tuttiEsami) {
			if(!parziale.contains(e)) {
				if(getCrediti(parziale)+e.getCrediti()<=crediti) {
					parziale.add(e);
					this.cerca(parziale, crediti, 0);
					parziale.remove(parziale.size()-1);
					//this.cerca(parziale, crediti);
				}
			}
	}*/
		//secondo metodo
		if(L==tuttiEsami.size()) return;
		this.cerca(parziale, crediti, L+1);
		parziale.add(tuttiEsami.get(L));
		this.cerca(parziale, crediti, L+1);
		parziale.remove(tuttiEsami.get(L));
		
		
		 
	}


	private double getMedia(List<Esame> parziale) {
		double media=0;
		double somma=0;
		if(parziale.size()==1) {
			return parziale.get(0).getVoto();
		}
		int i=0;
		for(Esame e: parziale) {
			somma+=e.getVoto();
			i++;
		}
		media=somma/i;
		return media;
	}


	private int getCrediti(List<Esame> parziale) {
		int crediti=0;
		
		for(Esame e: parziale) {
			crediti+=e.getCrediti();
			
		}
		return crediti;
	}

}
