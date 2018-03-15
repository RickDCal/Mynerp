Ext.define('Mynerp.view.comercial.coleta.ColetasController', {
    extend: 'Mynerp.view.base.ViewController',

    alias: 'controller.coletas',

    requires: [
        'Mynerp.view.comercial.coleta.ColetaWindow',
        'Mynerp.view.base.ViewController'
        //'Mynerp.ux.grid.Printer'
    ],

    createDialog: function(record){

        var me = this,
            view = me.getView(),
            glyphs = Mynerp.util.Glyphs,
            parametroColeta = me.getStore('parametroColeta').getAt(0);
           
        me.isEdit = !!record;
        me.dialog = view.add({
            xtype: 'coleta-window',
            viewModel: {
                data: {
                    title: record ? 'Editar: ' + record.get('id') + ' - Cliente: ' + record.get('nomePessoa') : 'Cadastrar',
                    glyph: record ? glyphs.getGlyph('edit') : glyphs.getGlyph('add')
                },
                links: {
                    currentCadastro: record || {
                        type: 'Coleta',
                        //create: true
                        create: {
                             //id: '0'
                             idCobranca: parametroColeta.get('idCobranca'),
                             nomeCobranca: parametroColeta.get('nomeCobranca')
                        }
                    }
                }
            },
            session: true //child session
        });

        me.dialog.show();

    },

    onSave: function(button, e, options){

        var me = this,
            dialog = me.dialog,
            view = me.getView(),
            form = me.lookupReference('coletaForm'),
            isEdit = me.isEdit,
            session = me.getSession(),
            store = me.getStore('coletas'),
            id;

        if (form.isValid()) {
            if (!isEdit) {
                id = dialog.getViewModel().get('currentCadastro').id;
            }
            dialog.getSession().save(); //salvou as alterações da tela na sessão e no servidor, a store ainda não sabe
            if (!isEdit) {
                //me.getStore('clientes').add(session.getRecord('Cliente', id)); // aqui adiciona o registro salvo da store pra não precisar buscar denovo com o sync
                store.add(session.getRecord('Coleta', id)); // aqui adiciona o registro salvo da store pra não precisar buscar denovo com o sync
            }
            
            store.sync({
                success: function(form, action) {
                    me.onCancel(); //fecha a janelinha
                    me.refresh(); //chama outro método logo abaixo
                    Mynerp.util.Util.showToast(form.proxy.getReader().rawData.mensagemRetorno);
                    //Mynerp.util.Util.showToast('Cadastro salvo com sucesso!'); // mostar mensagem
                },
                failure: function(form, action) {
                    try {
                        Mynerp.util.Util.handleFormFailure(action);
                    } catch (e) {
                        Mynerp.util.Util.showErrorMsg('Houve uma falha no processamento da requisição. A janela será recarregada para conferência dos dados.');
                    } finally {
                        store.load();
                        me.onCancel();
                        //me.createDialog(store.model.load(id));
                    }
                    
                    
                }
            });

        }

    },

    onColetaSelect: function(id){
        var me = this,
            grid = me.lookupReference('coletasGrid'),
            store = me.getStore('coletas'),
            record = store.findRecord( 'id', id );

        if (record){
            grid.getSelectionModel().select(record);
        }
    },

    onItemClick: function( view, record, item, index, e, eOpts ) {
        this.redirectTo('coletas/' + record.get('id'));
    },

    onDbClick: function( grid, record, tr, rowIndex, e, eOpts ) { // eu que coloquei este evento    
        var me = this;
        me.createDialog(record);
    },

    getRecordSelected: function(button) {
        var grid  = button.up('coletas-grid');
            return grid.getSelection();
    },

    refresh: function(button, e, options) {
        var me = this,
            store = me.getStore('coletas');
        store.load();
    },

    init: function() { // função chamada no carregamento do controller
        var me = this;

        Ext.Ajax.request({
            url: 'coletaServlet?action=5',            
            success: function(response, opts) {
                var result = Mynerp.util.Util.decodeJSON(response.responseText),
                status = result.success;
                if (status) {
                    Ext.Msg.show({
                        title: 'Importar xml CT-e',
                        msg: 'Existem arquivos xml de conhecimentos de transporte na pasta de importação.<br> Deseja Importar?',
                        buttons: Ext.Msg.YESNO,
                        icon: Ext.Msg.QUESTION,
                        fn: function (buttonId) {
                            if (buttonId == 'yes') {
                                Ext.Ajax.request({
                                    url: 'coletaServlet?action=6',
                                    timeout: 120000, // 2 minutos
                                    success: function(response, opts){
                                        var result = Mynerp.util.Util.decodeJSON(response.responseText),
                                            resposta = result.mensagemRetorno;
                                        Mynerp.util.Util.showInfoMsg(resposta);
                                        me.refresh();
                                    },
                                    failure: function(response, opts) {
                                        var result = Mynerp.util.Util.decodeJSON(response.responseText),
                                            resposta = result.mensagemRetorno;
                                        if (resposta) {
                                           Mynerp.util.Util.showErrorMsg(resposta); 
                                        } else {
                                            Mynerp.util.Util.showErrorMsg('Houve uma falha no servidor. Status: ' + response.status);
                                        }
                                    }
                                });                               
                            } 
                        }
                    });
                }              
            },
            failure: function(response, opts) {
                var result = Mynerp.util.Util.decodeJSON(response.responseText),
                    resposta = result.mensagemRetorno;
                if (resposta) {
                    Mynerp.util.Util.showErrorMsg(resposta); 
                } else {
                    Mynerp.util.Util.showErrorMsg('Houve uma falha no servidor ao verificar XML de coletas. Status: ' + response.status);
                }
            }
        });
    }
});
