Ext.define('Mynerp.model.configuracao.Base', {
    extend: 'Mynerp.model.Base',

    requires: [
        'Mynerp.util.Util',
        'Mynerp.ux.data.writer.AssociatedWriter'
    ]//,

    /*
    fields: [
    {name: 'idParametro', reference: 'Parametro'}
    ]
    esta associação parece ter ficado errada e estava me atrapalhando a gravar uma nova conta a receber .... rank of null
	o problema provavelmente ocorre porque esta classe de base não é uma entidade (sem entityName).
    */

});