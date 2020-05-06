package br.com.codenation;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Comparator;
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

		if(checkTime(idTime)) {
			if(buscarTimePorId(idTime).getIdCapitao() != null) {
				return buscarTimePorId(idTime).getIdCapitao();
			} else {
				throw new CapitaoNaoInformadoException("O time não possui capitão definido!");
			}
		} else {
			throw new TimeNaoEncontradoException("Time não encontrado.");
		}

	}

	@Desafio("buscarNomeJogador")
	public String buscarNomeJogador(Long idJogador) {
		
		return buscarJogadorPorId(idJogador).getNome();

	}

	@Desafio("buscarNomeTime")
	public String buscarNomeTime(Long idTime) {

		return buscarTimePorId(idTime).getNome();

	}

	//ok
	@Desafio("buscarJogadoresDoTime")
	public List<Long> buscarJogadoresDoTime(Long idTime) {

		if(buscarTimePorId(idTime) != null) {
			List<Long> jogadoresTime = new ArrayList<>();

			for (Jogador jogador: jogadores) {
				if(jogador.getIdTime().longValue() == idTime){
					jogadoresTime.add(jogador.getId());
				}
			}

			jogadoresTime.sort(null);
			return jogadoresTime;
		}
		return new ArrayList<>();
	}

	@Desafio("buscarMelhorJogadorDoTime")
	public Long buscarMelhorJogadorDoTime(Long idTime) {

		List<Long> idsJogadores = buscarJogadoresDoTime(idTime);
		List<Jogador> jogadoresTime = new ArrayList<>();

		for(Long id: idsJogadores) {
			jogadoresTime.add(buscarJogadorPorId(id));
		}

		Comparator<Jogador> c = (h1, h2) -> h2.getNivelHabilidade().compareTo(h1.getNivelHabilidade());
		c = c.thenComparing(Jogador::getId);
		jogadoresTime.sort(c);

		return jogadoresTime.get(0).getId();

	}

	@Desafio("buscarJogadorMaisVelho")
	public Long buscarJogadorMaisVelho(Long idTime) {

		List<Long> idsJogadores = buscarJogadoresDoTime(idTime);
		List<Jogador> jogadoresTime = new ArrayList<>();

		for(Long id: idsJogadores) {
			jogadoresTime.add(buscarJogadorPorId(id));
		}

		Comparator<Jogador> c = Comparator.comparing(Jogador::getDataNascimento);
		c = c.thenComparing(Jogador::getId);
		jogadoresTime.sort(c);

		return jogadoresTime.get(0).getId();

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
		
	}

	@Desafio("buscarJogadorMaiorSalario")
	public Long buscarJogadorMaiorSalario(Long idTime) {
		
		List<Long> idsJogadores = buscarJogadoresDoTime(idTime);
		List<Jogador> jogadoresTime = new ArrayList<>();

		for(Long id: idsJogadores) {
			jogadoresTime.add(buscarJogadorPorId(id));
		}

		Comparator<Jogador> c = (s1, s2) -> s2.getSalario().compareTo(s1.getSalario());
		c = c.thenComparing(Jogador::getId);
		jogadoresTime.sort(c);

		return jogadoresTime.get(0).getId();
			
	}

	@Desafio("buscarSalarioDoJogador")
	public BigDecimal buscarSalarioDoJogador(Long idJogador) {

		return buscarJogadorPorId(idJogador).getSalario();

	}

	@Desafio("buscarTopJogadores")
	public List<Long> buscarTopJogadores(Integer top) {

		List<Long> tops = new ArrayList<>();
		
		if((jogadores.size() > 0) && (top > 0) ){
			Comparator<Jogador> c = (h1, h2) -> h2.getNivelHabilidade().compareTo(h1.getNivelHabilidade());
			c = c.thenComparing(Jogador::getId);
			jogadores.sort(c);

			for(Jogador jogador: jogadores) {
				if (tops.size() < top) {
					tops.add(jogador.getId());
				} else {
					break;
				}
			}
		}
		return tops;
	}

	@Desafio("buscarCorCamisaTimeDeFora")
	public String buscarCorCamisaTimeDeFora(Long timeDaCasa, Long timeDeFora) {

		Time timeCasa = buscarTimePorId(timeDaCasa);
		Time timeFora = buscarTimePorId(timeDeFora);
			
		if(timeCasa.getCorUniformePrincipal().equals(timeFora.getCorUniformePrincipal())) {
			return timeFora.getCorUniformeSecundario();
		} else {
			return timeFora.getCorUniformePrincipal();
		}

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
