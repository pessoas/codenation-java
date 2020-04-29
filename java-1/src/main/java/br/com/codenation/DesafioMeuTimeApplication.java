package br.com.codenation;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

import br.com.codenation.desafio.annotation.Desafio;
import br.com.codenation.desafio.app.MeuTimeInterface;
import br.com.codenation.desafio.entidades.Jogador;
import br.com.codenation.desafio.entidades.Time;
import br.com.codenation.desafio.exceptions.CapitaoNaoInformadoException;
import br.com.codenation.desafio.exceptions.IdentificadorUtilizadoException;
import br.com.codenation.desafio.exceptions.JogadorNaoEncontradoException;
import br.com.codenation.desafio.exceptions.TimeNaoEncontradoException;


public class DesafioMeuTimeApplication implements MeuTimeInterface {

	private List<Time> times = new ArrayList<>();
	private List<Jogador> jogadores = new ArrayList<>();


	public boolean validateTime(Time time) {
		if(time.getId() == null || time.getNome().equals(null) || time.getDataCriacao() == null ||
				time.getCorUniformePrincipal() == null || time.getGetCorUniformeSecundario() == null) {
			return false;
		}
		return true;
	}

	@Desafio("incluirTime")
	public void incluirTime(Long id, String nome, LocalDate dataCriacao, String corUniformePrincipal, String corUniformeSecundario) {
			if(checkTime(id)){
				throw new IdentificadorUtilizadoException("O id: "+id+", já esta cadastrado como outro time!");
			} else {
				this.times.add(new Time(id,nome,dataCriacao,corUniformePrincipal,corUniformeSecundario));
			}
	}


	@Desafio("incluirJogador")
	public void incluirJogador(Long id, Long idTime, String nome, LocalDate dataNascimento, Integer nivelHabilidade, BigDecimal salario) {

		if(checkTime(idTime)) {
			if(checkJogador(id)){
				throw new IdentificadorUtilizadoException("O id: "+id+", já esta cadastrado como outro jogador!");
			} else {
				this.jogadores.add(new Jogador(id, idTime, nome, dataNascimento, nivelHabilidade, salario));
			}
		} else {
			throw new TimeNaoEncontradoException("O Time com id: "+idTime+", não pode ser encontrado!");
		}
	}

	@Desafio("definirCapitao")
	public void definirCapitao(Long idJogador) {

		buscarTimePorId(buscarJogadorPorId(idJogador).getIdTime()).setIdCapitao(idJogador);

	}

	@Desafio("buscarCapitaoDoTime")
	public Long buscarCapitaoDoTime(Long idTime) {

		if(buscarTimePorId(idTime).getIdCapitao() != null) {
			return buscarTimePorId(idTime).getIdCapitao();
		} else {
			throw new CapitaoNaoInformadoException("O time não possui capitão definido!");
		}

		//throw new UnsupportedOperationException();
	}

	@Desafio("buscarNomeJogador")
	public String buscarNomeJogador(Long idJogador) {
		
		return buscarJogadorPorId(idJogador).getNome();

		//throw new UnsupportedOperationException();
	}

	@Desafio("buscarNomeTime")
	public String buscarNomeTime(Long idTime) {
		return buscarTimePorId(idTime).getNome();

		//throw new UnsupportedOperationException();
	}

	@Desafio("buscarJogadoresDoTime")
	public List<Long> buscarJogadoresDoTime(Long idTime) {
		
		if(buscarTimePorId(idTime) != null) {
			List<Long> jogadoresTime = new ArrayList<>();
			for (Jogador jogador: jogadores) {
				if(jogador.getIdTime().equals(idTime)){
					jogadoresTime.add(jogador.getId());
				}
			}
			return jogadoresTime;
		}
		return new ArrayList<>();
		//throw new UnsupportedOperationException();
	}

	@Desafio("buscarMelhorJogadorDoTime")
	public Long buscarMelhorJogadorDoTime(Long idTime) {
		List<Long> idsJogadores = buscarJogadoresDoTime(idTime);
		List<Jogador> jogadoresTime = new ArrayList<>();
		for(Long id: idsJogadores) {
			jogadoresTime.add(buscarJogadorPorId(id));
		}
		jogadoresTime.sort((i1, i2) -> i1.getId().compareTo(i2.getId()));
		jogadoresTime.sort((s1, s2) -> s1.getNivelHabilidade().compareTo(s2.getNivelHabilidade()));
		return jogadoresTime.get(jogadoresTime.size() - 1).getId();

		//throw new UnsupportedOperationException();
	}

	@Desafio("buscarJogadorMaisVelho")
	public Long buscarJogadorMaisVelho(Long idTime) {
		List<Long> idsJogadores = buscarJogadoresDoTime(idTime);
		List<Jogador> jogadoresTime = new ArrayList<>();
		for(Long id: idsJogadores) {
			jogadoresTime.add(buscarJogadorPorId(id));
		}
		jogadoresTime.sort((i1, i2) -> i1.getId().compareTo(i2.getId()));
		jogadoresTime.sort((n1, n2) -> n1.getDataNascimento().compareTo(n2.getDataNascimento()));
		return jogadoresTime.get(jogadoresTime.size() - 1).getId();

		//throw new UnsupportedOperationException();
	}

	@Desafio("buscarTimes")
	public List<Long> buscarTimes() {
		
		if(times.size() != 0) {
			List<Long> listaTimes = new ArrayList<>();
			for(Time time: times) {
				listaTimes.add(time.getId());
			}
			listaTimes.sort(null);
			return listaTimes;
		} else {
			return new ArrayList<>();
		}
		
		//throw new UnsupportedOperationException();
	}

	@Desafio("buscarJogadorMaiorSalario")
	public Long buscarJogadorMaiorSalario(Long idTime) {
		
		List<Long> idsJogadores = buscarJogadoresDoTime(idTime);
		List<Jogador> jogadoresTime = new ArrayList<>();
		for(Long id: idsJogadores) {
			jogadoresTime.add(buscarJogadorPorId(id));
		}
		jogadoresTime.sort((i1, i2) -> i1.getId().compareTo(i2.getId()));
		jogadoresTime.sort((s1, s2) -> s1.getSalario().compareTo(s2.getSalario()));
		return jogadoresTime.get(jogadoresTime.size() - 1).getId();
			
		//throw new UnsupportedOperationException();
	}

	@Desafio("buscarSalarioDoJogador")
	public BigDecimal buscarSalarioDoJogador(Long idJogador) {
		return buscarJogadorPorId(idJogador).getSalario();
		//throw new UnsupportedOperationException();
	}

	@Desafio("buscarTopJogadores")
	public List<Long> buscarTopJogadores(Integer top) {
		
		List<Long> tops = new ArrayList<>();
		jogadores.sort((i1, i2) -> i1.getId().compareTo(i2.getId()));
		jogadores.sort((s1, s2) -> s1.getNivelHabilidade().compareTo(s2.getNivelHabilidade()));

		for(Jogador jogador: jogadores) {
			if (tops.size() <= top) {
				tops.add(jogador.getId());
			} else {
				break;
			}
		}
		
		return tops;
		//throw new UnsupportedOperationException();
	}

	@Desafio("buscarCorCamisaTimeDeFora")
	public String buscarCorCamisaTimeDeFora(Long timeDaCasa, Long timeDeFora) {
		
		//Time timeCasa;
		//Time timeFora;
		Time timeCasa = buscarTimePorId(timeDaCasa);
		Time timeFora = buscarTimePorId(timeDeFora);
			
		if(timeCasa.getCorUniformePrincipal().equals(timeFora.getCorUniformePrincipal())) {
			return timeFora.getGetCorUniformeSecundario();
		} else {
			return timeFora.getCorUniformePrincipal();
		}

		//return null;
		//throw new UnsupportedOperationException();
	}

	public Time buscarTimePorId(Long timeId){
		for (Time time: times) {
			if(time.getId().equals(timeId)){
				return time;
			}
		}
		throw new TimeNaoEncontradoException("O Time com id: "+timeId+", não pode ser encontrado!");
	}

	public Jogador buscarJogadorPorId(Long jogadorId){
		for (Jogador jogador: jogadores) {
			if(jogador.getId().equals(jogadorId)){
				return jogador;
			}
		}
		throw new JogadorNaoEncontradoException("O Jogador com id: "+jogadorId+", não pode ser encontrado!");
	}

	public boolean checkTime(Long id) {
		for(Time time: times) {
			if(time.getId().equals(id)) {
				return true;
			}
		}
		return false;
	}

	public boolean checkJogador(Long id) {
		for(Jogador jogador: jogadores) {
			if(jogador.getId().equals(id)) {
				return true;
			}
		}
		return false;
	}
}
