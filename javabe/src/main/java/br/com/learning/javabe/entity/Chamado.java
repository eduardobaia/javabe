package br.com.learning.javabe.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.ManyToAny;

import br.com.learning.javabe.enumerado.Tipo;

@Entity
@Table(name = "CHAMADO")
public class Chamado implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "ASSUNTO")
	private String assunto;

	@Column(name = "MENSAGEM")
	private String mensagem;
	@Column(name = "STATUS")
	private String status;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_registro", nullable = false, updatable = false)
	private Date dataRegistro;
	@Enumerated(EnumType.STRING)
	@Column(length = 16, nullable = false)
	private Tipo tipo;

	@ManyToOne(optional = false)
	@JoinColumn(nullable = false)
	private Usuario usuario;

	
	@ManyToOne
	@JoinColumn(name="usuario_status")
	private Usuario usuarioStatus;


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getAssunto() {
		return assunto;
	}


	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}


	public String getMensagem() {
		return mensagem;
	}


	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public Date getDataRegistro() {
		return dataRegistro;
	}


	public void setDataRegistro(Date dataRegistro) {
		this.dataRegistro = dataRegistro;
	}


	public Tipo getTipo() {
		return tipo;
	}


	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}


	public Usuario getUsuario() {
		return usuario;
	}


	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}


	public Usuario getUsuarioStatus() {
		return usuarioStatus;
	}


	public void setUsuarioStatus(Usuario usuarioStatus) {
		this.usuarioStatus = usuarioStatus;
	}


	@Override
	public String toString() {
		return "Chamado [id=" + id + ", assunto=" + assunto + ", mensagem=" + mensagem + ", status=" + status
				+ ", dataRegistro=" + dataRegistro + ", tipo=" + tipo + ", usuario=" + usuario + ", usuarioStatus="
				+ usuarioStatus + "]";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((assunto == null) ? 0 : assunto.hashCode());
		result = prime * result + ((dataRegistro == null) ? 0 : dataRegistro.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((mensagem == null) ? 0 : mensagem.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
		result = prime * result + ((usuario == null) ? 0 : usuario.hashCode());
		result = prime * result + ((usuarioStatus == null) ? 0 : usuarioStatus.hashCode());
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
		Chamado other = (Chamado) obj;
		if (assunto == null) {
			if (other.assunto != null)
				return false;
		} else if (!assunto.equals(other.assunto))
			return false;
		if (dataRegistro == null) {
			if (other.dataRegistro != null)
				return false;
		} else if (!dataRegistro.equals(other.dataRegistro))
			return false;
		if (id != other.id)
			return false;
		if (mensagem == null) {
			if (other.mensagem != null)
				return false;
		} else if (!mensagem.equals(other.mensagem))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (tipo != other.tipo)
			return false;
		if (usuario == null) {
			if (other.usuario != null)
				return false;
		} else if (!usuario.equals(other.usuario))
			return false;
		if (usuarioStatus == null) {
			if (other.usuarioStatus != null)
				return false;
		} else if (!usuarioStatus.equals(other.usuarioStatus))
			return false;
		return true;
	}
	
	
	
}
