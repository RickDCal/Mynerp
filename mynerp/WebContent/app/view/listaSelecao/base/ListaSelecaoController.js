Ext.define('Mynerp.view.listaSelecao.base.ListaSelecaoController', {
    extend: 'Mynerp.view.base.ViewController',

    requires: [

    ],

    alias: 'controller.listaselecao',

    onListaSelecaoDbClick: function( grid, record, tr, rowIndex, e, eOpts ) { // eu que coloquei este evento    
        var me = this,
        codigo = record.get('id'),
        nome = record.get('nome'), // se precisar ser outro campo posso colocar a atualização do campo de nome no controller filho em um método a parte
        janela = grid.up('listawindowform'),
        campoCodigo = janela.campoCodigo,
        campoNome = janela.campoNome,
        tipo = janela.chamador;

        switch (tipo) {
            case 1: //campo de código em form
                campoCodigo.setValue(codigo);
                if (campoNome) {campoNome.setValue(nome);}
            break;
            case 2 : //campo nome em form
            break;
            case 3 : //campo de código em célula do grid
            break;
            case 4 : //campo de nome em célula do grid
                grid2 = campoNome.up('grid'); //grid2 é o grid onde temos que colocar os valores
                rec = grid2.getSelection()[0];
                atributoNome = campoNome.nomeDataIndex;
                atributoCodigo = campoNome.codigoDataIndex;
                rec.set(atributoCodigo,codigo);
                rec.set(atributoNome,nome);
                campoNome.getTrigger('limpar').show();                
            break;
            default:
            break;
        }
        janela.close();
        
    },

    onListaSelecaoCancel: function (button, e, options) {
        var me = this,
        janela = button.up('listawindowform');
        janela.close();
    },

    onListaSelecaoConfirm: function(button, e, options) {
        var me = this,
            janela = button.up('listawindowform'),
            grid = janela.down('panel'),
            records = grid.getSelection(), rec;
            if (records.length > 0) {
                rec = records[0];
                me.onListaSelecaoDbClick(grid, rec);
            } else {
                Mynerp.util.Util.showInfoMsg('Nenhum registro foi selecionado.');
            }            
    }

});
