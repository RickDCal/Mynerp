Ext.define('Mynerp.view.cadastro.cliente.ClientesController', {
    extend: 'Mynerp.view.base.ViewController',

    // Esta classe é bem semelhante à do exemplo no kitchensink "isolated child session" no controller

    alias: 'controller.clientes',

    requires: [
        'Mynerp.view.cadastro.cliente.ClienteWindow',
        'Mynerp.view.base.ViewController'
        //'Mynerp.ux.grid.Printer'
    ],

    createDialog: function(record){

        var me = this,
            view = me.getView(),
            glyphs = Mynerp.util.Glyphs;

        me.isEdit = !!record;
        me.dialog = view.add({
            xtype: 'cliente-window',
            viewModel: {
                data: {
                    title: record ? 'Editar: ' + record.get('id') + ' - ' + record.get('nome') : 'Cadastrar',
                    glyph: record ? glyphs.getGlyph('edit') : glyphs.getGlyph('add')
                },
                links: {
                    currentCadastro: record || {
                        type: 'Cliente',
                        create: true
                        // create: {
                        //     id: '0'
                        // }
                    }
                }
            },
            session: true //child session
        });

        me.dialog.show();

    },

    /*
    onEdit: function (button) { // override no onEdit da base.ViewController
        var me = this;
        try {
            me.createDialog(button.getWidgetRecord()); // se o botão estiver na linha do grid
        } catch (e) {// se o botão estiver na barra
            records = me.getRecordSelected(button);
            me.createDialog(records[0]); 
        }                

    },
    */

    onDeleteFromToolbar: function (button, e, options) {//apenas para referência. a função que está no pai funciona para este procedimento
        var me = this,            
            store = me.getStore('clientes'),            
            record;

        try {
            record = button.getWidgetRecord(); // se o botão estiver na linha do grid
        } catch (e){
            record = me.getRecordSelected(button)[0]; // se o botão estiver na barra de ferramentas
        }

        if (!!record) {
            me.delete(record, store);
        } else {
            Mynerp.util.Util.showAlertMsg('Nenhum registro selecionado para exclusão.');
            
        } 

    },

    onSave: function(button, e, options){

        var me = this,
            dialog = me.dialog,
            view = me.getView(),
            form = me.lookupReference('clienteForm'),

            isEdit = me.isEdit,
            session = me.getSession(),
            store = me.getStore('clientes'),
            id;

        if (form.isValid()) {
            if (!isEdit) {
                id = dialog.getViewModel().get('currentCadastro').id;
            }
            dialog.getSession().save(); //salvou as alterações da tela na sessão e no servidor, a store ainda não sabe
            if (!isEdit) {
                //me.getStore('clientes').add(session.getRecord('Cliente', id)); // aqui adiciona o registro salvo da store pra não precisar buscar denovo com o sync
                store.add(session.getRecord('Cliente', id)); // aqui adiciona o registro salvo da store pra não precisar buscar denovo com o sync
            }
            
            //me.viewSessionChanges(); // isso é para testes apenas        

            //este trecho abaixo é que salva os dados lá no servidor quando usamos batch... preferi o store.synco por causa dos callbacks sucess e failure

            // var batch = session.getSaveBatch(); //o batch é que gerencia as pendências
            // if (batch){
            //     batch.start(); // aqui é que faz as requisições no servidor

            //     // if (batch.isComplete() && !batch.hasException()) {
            //     //     Mynerp.util.Util.showToast('Cadastro nº ' + id + ' salvo com sucesso!');
            //     // }

            //     me.onCancel();
            //     me.refresh();
            //  }

            store.sync({
                success: function(form, action) {
                    me.onCancel(); //fecha a janelinha
                    me.refresh(); //chama outro método logo abaixo
                    Mynerp.util.Util.showToast(form.proxy.getReader().rawData.mensagemRetorno);
                    //Mynerp.util.Util.showToast('Cadastro salvo com sucesso!'); // mostar mensagem
                },
                failure: function(form, action) {
                    Mynerp.util.Util.handleFormFailure(action);
                }
            });

        }

    },

    onClienteSelect: function(id){
        var me = this,
            grid = me.lookupReference('clientesGrid'),
            store = me.getStore('clientes'),
            record = store.findRecord( 'id', id );

        if (record){
            grid.getSelectionModel().select(record);
        }
    },

    onItemClick: function( view, record, item, index, e, eOpts ) {
        this.redirectTo('clientes/' + record.get('id'));
    },    

    onDbClick: function( grid, record, tr, rowIndex, e, eOpts ) { // eu que coloquei este evento    
        var me = this;
        me.createDialog(record);
    },

    getRecordSelected: function(button) {
        var grid  = button.up('clientes-grid');
            return grid.getSelection();
    },
    
    refresh: function(button, e, options) {
        var me = this,
            store = me.getStore('clientes');
        store.load();
    },

    onChangeTipoPessoa: function(field, newValue, oldValue, eOpts) {
        var me = this,
            form = me.lookupReference('clienteForm'),
            cnpj = me.lookupReference('cnpjCliente');
            nome = me.lookupReference('nomeCliente');
            sexo = me.lookupReference('sexoCliente');
            nascimento = me.lookupReference('nascimentoCliente');
            fantasia = me.lookupReference('fantasiaCliente');

            cnpj.reset();

            if (newValue == 1) {
                cnpj.setFieldLabel('CPF');
                nome.setFieldLabel('Nome');
                sexo.setHidden(false);
                nascimento.setHidden(false);
                fantasia.setHidden(true);
            } else if (newValue == 2) {
                cnpj.setFieldLabel('CNPJ');
                nome.setFieldLabel('Razão Social');
                sexo.setHidden(true);
                nascimento.setHidden(true);
                fantasia.setHidden(false);
            }
        
    },

    formataCpfCnpj: function(field, e, options) {
        var me = this, 
            tipoPessoa = me.lookupReference('tipoPessoa').getValue(),
            input = field.getValue(),
            tipo;

            Mynerp.util.Util.formataCpfCnpj(field, e, tipoPessoa);            
    }


    //onPrint: function(button, e, options) {
    //     var printer = Mynerp.ux.grid.Printer;
    //     printer.printAutomatically = false;
    //     printer.print(this.lookupReference('clientesGrid'));
    // },

    // onExportPDF: function(button, e, options) {
    //     var mainPanel = Ext.ComponentQuery.query('mainpanel')[0];

    //     var newTab = mainPanel.add({
    //         xtype: 'panel',
    //         closable: true,
    //         glyph: Mynerp.util.Glyphs.getGlyph('pdf'),
    //         title: 'Clientes PDF',
    //         layout: 'fit',
    //         html: 'loading PDF...',
    //         items: [{
    //             xtype: 'uxiframe',
    //             src: 'php/pdf/exportFilmsPdf.php'
    //         }]
    //     });

    //     mainPanel.setActiveTab(newTab);
    // },

    // onExportExcel: function(button, e, options) {
    //     window.open('php/pdf/exportFilmsExcel.php');
    // },



    // viewSessionChanges: function () {
    //     var changes = this.getView().getSession().getChanges();
    //     if (changes !== null) {
    //         new Ext.window.Window({
    //             autoShow: true,
    //             title: 'Session Changes',
    //             modal: true,
    //             width: 600,
    //             height: 400,
    //             layout: 'fit',
    //             items: {
    //                 xtype: 'textarea',
    //                 value: JSON.stringify(changes, null, 4)
    //             }
    //         });
    //     } else {
    //         Ext.Msg.alert('No Changes', 'There are no changes to the session.');
    //     }
    // }


});
