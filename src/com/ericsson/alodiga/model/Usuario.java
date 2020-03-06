package com.ericsson.alodiga.model;

import com.alodiga.wallet.ws.Cumplimient;
import com.ericsson.alodiga.respuestas.RespuestaListadoProducto;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.hibernate.validator.constraints.Email;

import com.ericsson.alodiga.validations.CheckAge;
import javax.persistence.Transient;
import javax.swing.text.StyledEditorKit;

@XmlRootElement(name = "Usuario")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "usuario")
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u")
    ,
		@NamedQuery(name = "Usuario.findByEmail", query = "SELECT u FROM Usuario u where u.email=:email")
    ,
		@NamedQuery(name = "Usuario.findByMovil", query = "SELECT u FROM Usuario u where u.movil=:movil")
    ,
		@NamedQuery(name = "Usuario.findUserAloPos", query = "SELECT u FROM Usuario u JOIN u.perfilAloPos pa JOIN pa.empresa e WHERE pa.tipoPersona = :tipoPersona AND e.empresaId = :empresaId")
    ,
		@NamedQuery(name = "Usuario.findByCuenta", query = "SELECT  u FROM Usuario u JOIN u.cuenta c  WHERE   c.numeroCuenta = :nCuenta")
    ,
		@NamedQuery(name = "Usuario.findByTarjeta", query = "SELECT  u FROM Usuario u JOIN u.tarjetaUsuarios t JOIN t.tarjeta ta WHERE   ta.numeroTarjeta = :nTarjeta")}
)
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @XmlElement(name = "UsuarioID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private int usuarioId;

    @XmlElement(name = "apellido")
    @Column(nullable = false, length = 100)
    @NotNull(message = "{null.lastname}")
    private String apellido;

    @XmlElement(name = "credencial")
    @Column(nullable = false, length = 10000)
    @NotNull(message = "{null.password}")
    private String credencial;

    @XmlElement(name = "credencialFecha")
    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date credencialFecha;

    @XmlElement(name = "email")
    @Column(nullable = false, length = 100, unique = true)
    @NotNull(message = "{null.email}")
    @Email(message = "{invalid.email}")
    private String email;

    @XmlElement(name = "fechaNacimiento")
    @Temporal(TemporalType.DATE)
    @CheckAge(minAge = 14, maxAge = 65, message = "{invalid.birthdate}")
    private Date fechaNacimiento;

    @XmlElement(name = "intentosFallidos")
    @Column(nullable = false)
    private int intentosFallidos;

    @XmlElement(name = "movil")
    @Column(nullable = false, length = 20, unique = true)
    @NotNull(message = "{null.phonenumber}")
    @Pattern(regexp = "\\d{10,16}", message = "{invalid.phonenumber}")
    private String movil;

    @XmlElement(name = "nombre")
    @Column(nullable = false, length = 100)
    @NotNull(message = "{null.name}")
    private String nombre;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "aplicacionUsuario", joinColumns = {
        @JoinColumn(name = "usuarioId")}, inverseJoinColumns = {
        @JoinColumn(name = "aplicacionId")})
    private List<Aplicacion> aplicaciones;

    @XmlTransient
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Bitacora> bitacoras;

    @XmlTransient
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<DireccionConfianza> direccionConfianzas;

    @XmlTransient
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<PreguntaSecreta> preguntaSecretas;

    @Transient
    private List<RespuestaListadoProducto> respuestaListadoProductos;
            
    @Transient
    private String cumplimient;        

    @Transient
    private Boolean prepayCardAsociate;
    
    @Transient
    private Boolean prepayCard;
    
    @Transient
    private String numberCard;
    
    @Transient
    private int countrySourceId;
    // @XmlTransient
    // @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    // private List<SesionUsuario> sesionUsuarios;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "direccionId", nullable = false)
    private Direccion direccion;

    @ManyToOne
    @JoinColumn(name = "estadoId", nullable = false)
    private Estado estado;

    // bi-directional many-to-one association to Imagen
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "imagenId")
    private Imagen imagen;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "cuentaId", nullable = false)
    private Cuenta cuenta;

    @Column
    private String genero;

    @Column
    private String telefonoResidencial;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "ocupacionId")
    private Ocupacion ocupacion;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "tipoDocumentoId")
    private TipoDocumento tipoDocumento;

    @Column
    private String numeroDocumento;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "perfilAloESPId")
    private PerfilAloESP perfilAloEsp;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "perfilAloPosId")
    private PerfilAloPos perfilAloPos;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "perfilAloRRPId")
    private PerfilAloRRP perfilAloRrp;

    @Column
    private String pin;

    // bi-directional many-to-one association to CuentaBancaria
    @OneToMany(mappedBy = "usuario")
    private List<CuentaBancaria> cuentaBancarias;

    // bi-directional many-to-one association to TarjetaUsuario
    @OneToMany(mappedBy = "usuario")
    private List<TarjetaUsuario> tarjetaUsuarios;

    public Usuario() {
    }

    public int getUsuarioId() {
        return this.usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getApellido() {
        return this.apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCredencial() {
        return this.credencial;
    }

    public void setCredencial(String credencial) {
        this.credencial = credencial;
    }

    public Date getCredencialFecha() {
        return this.credencialFecha;
    }

    public void setCredencialFecha(Date credencialFecha) {
        this.credencialFecha = credencialFecha;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getFechaNacimiento() {
        return this.fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public int getIntentosFallidos() {
        return this.intentosFallidos;
    }

    public void setIntentosFallidos(int intentosFallidos) {
        this.intentosFallidos = intentosFallidos;
    }

    public String getMovil() {
        return this.movil;
    }

    public void setMovil(String movil) {
        this.movil = movil;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Aplicacion> getAplicaciones() {
        return this.aplicaciones;
    }

    public void setAplicacionUsuarios(List<Aplicacion> aplicaciones) {
        this.aplicaciones = aplicaciones;
    }

    public List<Bitacora> getBitacoras() {
        return this.bitacoras;
    }

    public void setBitacoras(List<Bitacora> bitacoras) {
        this.bitacoras = bitacoras;
    }

    public Bitacora addBitacora(Bitacora bitacora) {
        getBitacoras().add(bitacora);
        bitacora.setUsuario(this);

        return bitacora;
    }

    public Bitacora removeBitacora(Bitacora bitacora) {
        getBitacoras().remove(bitacora);
        bitacora.setUsuario(null);

        return bitacora;
    }

    public List<DireccionConfianza> getDireccionConfianzas() {
        return this.direccionConfianzas;
    }

    public void setDireccionConfianzas(
            List<DireccionConfianza> direccionConfianzas) {
        this.direccionConfianzas = direccionConfianzas;
    }

    public List<PreguntaSecreta> getPreguntaSecretas() {
        return this.preguntaSecretas;
    }

    public void setPreguntaSecretas(List<PreguntaSecreta> preguntaSecretas) {
        this.preguntaSecretas = preguntaSecretas;
    }

    // public List<SesionUsuario> getSesionUsuarios() {
    // return this.sesionUsuarios;
    // }
    // public void setSesionUsuarios(List<SesionUsuario> sesionUsuarios) {
    // this.sesionUsuarios = sesionUsuarios;
    // }
    public Direccion getDireccion() {
        return this.direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    public Estado getEstado() {
        return this.estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Imagen getImagen() {
        return this.imagen;
    }

    public void setImagen(Imagen imagen) {
        this.imagen = imagen;
    }

    public Cuenta getCuenta() {
        return this.cuenta;
    }

    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }

    public PerfilAloESP getPerfilAloEsp() {
        return this.perfilAloEsp;
    }

    public void setPerfilAloEsp(PerfilAloESP perfilAloEsp) {
        this.perfilAloEsp = perfilAloEsp;
    }

    public PerfilAloPos getPerfilAloPos() {
        return this.perfilAloPos;
    }

    public void setPerfilAloPos(PerfilAloPos perfilAloPos) {
        this.perfilAloPos = perfilAloPos;
    }

    public PerfilAloRRP getPerfilAloRrp() {
        return this.perfilAloRrp;
    }

    public void setPerfilAloRrp(PerfilAloRRP perfilAloRrp) {
        this.perfilAloRrp = perfilAloRrp;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getTelefonoResidencial() {
        return telefonoResidencial;
    }

    public void setTelefonoResidencial(String telefonoResidencial) {
        this.telefonoResidencial = telefonoResidencial;
    }

    public Ocupacion getOcupacion() {
        return ocupacion;
    }

    public void setOcupacion(Ocupacion ocupacion) {
        this.ocupacion = ocupacion;
    }

    public TipoDocumento getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(TipoDocumento tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public List<CuentaBancaria> getCuentaBancarias() {
        return this.cuentaBancarias;
    }

    public void setCuentaBancarias(List<CuentaBancaria> cuentaBancarias) {
        this.cuentaBancarias = cuentaBancarias;
    }

    public CuentaBancaria addCuentaBancaria(CuentaBancaria cuentaBancaria) {
        getCuentaBancarias().add(cuentaBancaria);
        cuentaBancaria.setUsuario(this);

        return cuentaBancaria;
    }

    public CuentaBancaria removeCuentaBancaria(CuentaBancaria cuentaBancaria) {
        getCuentaBancarias().remove(cuentaBancaria);
        cuentaBancaria.setUsuario(null);

        return cuentaBancaria;
    }

    public List<TarjetaUsuario> getTarjetaUsuarios() {
        return this.tarjetaUsuarios;
    }

    public void setTarjetaUsuarios(List<TarjetaUsuario> tarjetaUsuarios) {
        this.tarjetaUsuarios = tarjetaUsuarios;
    }

    public TarjetaUsuario addTarjetaUsuario(TarjetaUsuario tarjetaUsuario) {
        getTarjetaUsuarios().add(tarjetaUsuario);
        tarjetaUsuario.setUsuario(this);

        return tarjetaUsuario;
    }

    public TarjetaUsuario removeTarjetaUsuario(TarjetaUsuario tarjetaUsuario) {
        getTarjetaUsuarios().remove(tarjetaUsuario);
        tarjetaUsuario.setUsuario(null);

        return tarjetaUsuario;
    }

    public String getPin() {
        return this.pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public List<RespuestaListadoProducto> getRespuestaListadoProductos() {
        return respuestaListadoProductos;
    }

    public void setRespuestaListadoProductos(List<RespuestaListadoProducto> respuestaListadoProductos) {
        this.respuestaListadoProductos = respuestaListadoProductos;
    }

    public String getCumplimient() {
        return cumplimient;
    }

    public void setCumplimient(String cumplimient) {
        this.cumplimient = cumplimient;
    }

    public Boolean getPrepayCardAsociate() {
        return prepayCardAsociate;
    }

    public void setPrepayCardAsociate(Boolean prepayCardAsociate) {
        this.prepayCardAsociate = prepayCardAsociate;
    }

    public Boolean getPrepayCard() {
        return prepayCard;
    }

    public void setPrepayCard(Boolean prepayCard) {
        this.prepayCard = prepayCard;
    }

    public String getNumberCard() {
        return numberCard;
    }

    public void setNumberCard(String numberCard) {
        this.numberCard = numberCard;
    }

    public int getCountrySourceId() {
        return countrySourceId;
    }

    public void setCountrySourceId(int countrySourceId) {
        this.countrySourceId = countrySourceId;
    }


    
    
}
