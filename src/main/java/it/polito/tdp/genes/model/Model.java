package it.polito.tdp.genes.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;
import org.jgrapht.traverse.DepthFirstIterator;

import it.polito.tdp.genes.db.GenesDao;



public class Model {
	
	private Graph <Classification, DefaultWeightedEdge> grafo; 
	private GenesDao dao; 
	List <Arco> archi; 
	List <Classification> vertici; 
	private Set <DefaultWeightedEdge> archi2;
	List<Classification> aggiunti = new ArrayList <>(); 

	public Model() {
		dao= new GenesDao(); 
		creaGrafo(); 
	}
	
	public void creaGrafo() {
		grafo= new SimpleWeightedGraph<>(DefaultWeightedEdge.class) ;
		//aggiungere i vertici
		Graphs.addAllVertices(this.grafo, this.dao.getVertices()); 
		//aggiungere gli archi
		vertici= new ArrayList <Classification>( this.dao.getVertices()); 
		archi= new ArrayList<Arco>(this.dao.getEdges()); 
		List <Arco> archi2= new ArrayList <Arco> (pulisciArchi(archi)); 
		
		for (Arco a: archi2) {
			 
			
			DefaultWeightedEdge edge = this.grafo.getEdge(a.getC1(),a.getC2());
			if(edge == null) {
				Graphs.addEdgeWithVertices(this.grafo, a.getC1(), a.getC2(), 1);
			} else {
				double pesoVecchio = this.grafo.getEdgeWeight(edge);
				double pesoNuovo = pesoVecchio + 1;
				this.grafo.setEdgeWeight(edge, pesoNuovo);
			
			}
		}
		
	}
	
	private List <Arco> pulisciArchi(List<Arco> archi) {
		for (int i=0; i<archi.size(); i++) {
			for (int j=0; j<archi.size();j++) {
				if((archi.get(i).getC1().equals(archi.get(j).getC2()))  && (archi.get(i).getC2().equals(archi.get(j).getC1()))  && (archi.get(i).getType().compareTo(archi.get(j).getType())==0)) {
					archi.remove(j); 
				}
			}	
	}
		return archi; 
	}

	public int nVertici() {
		return this.grafo.vertexSet().size();
	}
	
	public List<Classification> getVertici() {
		return this.vertici;
	}
	
	public int nArchi() {
		return this.grafo.edgeSet().size();
	}
	
	public String getComponenteConnessa(Classification c) {
		
		List <ComponenteConnessa> connesse= new ArrayList <ComponenteConnessa>(); 
		List<Classification> adiacenti= new ArrayList<Classification>(); 
		Set<Classification> componenteConnessa; 
		List<Classification> prova= new ArrayList<Classification>(); 
		String result=""; 
		
		componenteConnessa= new HashSet<>(); 
		DepthFirstIterator <Classification, DefaultWeightedEdge> it= new DepthFirstIterator  <Classification, DefaultWeightedEdge>(this.grafo, c);
		while(it.hasNext()) {
			componenteConnessa.add(it.next());
			prova= new ArrayList<Classification>(componenteConnessa); 
			result= result+ "Adiacenti a: "+ prova.get(prova.size()-1).toString()+"\n"; 
			adiacenti= new ArrayList <>(Graphs.neighborListOf(this.grafo, prova.get(prova.size()-1))); 
			for (Classification cl: adiacenti) {
				if (!cl.equals(prova.get(prova.size()-1))) {
				DefaultWeightedEdge edge = this.grafo.getEdge(prova.get(prova.size()-1),cl);
				connesse.add(new ComponenteConnessa(cl,this.grafo.getEdgeWeight(edge))); 
				result=result+" "+connesse.get(connesse.size()-1).toString(); 
			}
			}
		}
		 
		return result; 
	}

}