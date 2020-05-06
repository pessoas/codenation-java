package challenge;

import java.util.ArrayList;
import java.util.List;
import challenge.EstacionamentoException;

public class Estacionamento {

	private List<Carro> carros = new ArrayList<>();
	private static final int MAX_CARROS = 10;

    public void estacionar(Carro carro) {
    	if(checkCarro(carro) ) {
    		if(checkMotorista(carro.getMotorista())) {
    			if(carros.size() < MAX_CARROS) {
    			carros.add(carro);
    		} else if (carros.size() == MAX_CARROS){
	    	  if(todosIdosos()) {
	    		  throw new EstacionamentoException("estacionamento cheio");
	    	  }
	    	  int i = 0;
	    	  for(Carro carrinho: carros) {
	    		  if(carrinho.getMotorista().getIdade() < 55) {
	    			  carros.remove(i);
	    			  carros.add(carro);
	    			  break;
	    		  } else {
	    			  i++;
	    		  }
	    	  } 
	    	}
    		} else {
    			throw new EstacionamentoException("motorista invalido");
    		}
    		
      } else {
    	  throw new EstacionamentoException("carro invalido");
      }
    }

    public int carrosEstacionados() {
    	
        return carros.size();
    }

    public boolean carroEstacionado(Carro carro) {
    	for(Carro carrinho: carros) {
    		if(carrinho.equals(carro)) {
    			return true;
    		}
    	}
        return false;
    }
    
    public boolean todosIdosos() {
    	int i = 0;
  	  	for(Carro carrinho: carros) {
  	  		if( carrinho.getMotorista().getIdade() >= 55 ) {
  	  			i++;
  	  		} 
  	  		if (i == carros.size()) {
  	  			return true;
  	  		}
  	  	}
  	  	return false;
    }
    
    public boolean checkMotorista(Motorista motorista) {
    	if(motorista == null) {
    		return false;
    	} else if (motorista.getIdade() < 18) {
    		return false;
    	} else if (motorista.getHabilitacao() == null) {
    		return false;
    	} else if (motorista.getPontos() > 20) {
    		return false;
    	} else if (motorista.getNome() == null) {
    		return false;
    	}
    	return true;
    }
    
    public boolean checkCarro(Carro carro) {
    	if (carro == null) {
    		return false;
    	} else if (carro.getCor() == null) {
    		return false;
    	} else if (carro.getPlaca() == null) {
    		return false;
    	} else if (carro.getMotorista() == null) {
    		return false;
    	} 
    	return true;
    }
    
   
}
