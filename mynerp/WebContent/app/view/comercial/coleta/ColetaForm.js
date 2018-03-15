Ext.define('Mynerp.view.comercial.coleta.ColetaForm', {
	extend: 'Ext.form.Panel',
	xtype: 'coleta-form',

	//reference: 'clienteForm', // já foi passado na window, referente a este form que é item dela

	requires: [
   		'Mynerp.util.Util',
   		'Mynerp.util.Glyphs'
   ],

			bodyPadding: 10,
			//width: 800, // o layout da janela é fit então não precisa destas medidas qui no filho, pois ajanela já tem medida
			//height: 500,
			layout: {
			type: 'anchor'
			},

			modelValidation: true, 
			scrollable: true,

			defaults: { //será aplicado no próximo array de itens que ele encontrar
				xtype: 'fieldset',
				layout: 'anchor',
				defaults: {
				anchor: '100%'
				}
			},        

			items: [
				//identificação
				{
				xtype: 'fieldset',
				title: 'Dados da coleta',

				items: [
					   {
					   xtype: 'container',
					   layout: 'hbox',
					   combineErrors: true,
					   defaults: {
						   flex: 1,
						   xtype: 'textfield',
						   maxLength: 100,
						   enforceMaxLength: true,
						   maxLengthText: 'Máximo de {0} caracteres.',
						   msgTarget: 'side',
						   labelWidth: 105,
						   labelAlign: 'top',
						   labelPad: 2,
						   padding: '0 10 10 0'
					   },
					   items: [{
						   name: 'id',
						   fieldLabel: 'Código',
						   width: 110,
						   editable: false,
						   flex: 1,
						   bind : '{currentCadastro.id}'
						   //allowBlank: false
						   },
						   {
						   fieldLabel: 'Código',
						   reference: 'codCliente',
						   xtype: 'getidfield',
			               xtypeLista: 'lista-cliente-window',
			               modelName: Mynerp.model.cadastro.Cliente,
			               nameFieldReference: 'nomeDoCliente',						   
						   maskRe: /[0-9]/i,
						   maxLength: 6,
						   flex: 1,
						   allowBlank: false,
						   afterLabelTextTpl: Mynerp.util.Util.required,
						   bind : '{currentCadastro.idPessoa}'
						   },
						   {
						   fieldLabel: 'Cliente',
						   reference: 'nomeDoCliente',
						   maxLength: 60,
						   flex: 5,
						   editable: false,
						   bind : '{currentCadastro.nomePessoa}'
					   	   },
						   {
						   xtype: 'datefield',
						   fieldLabel: 'Data',
						   flex: 3,
						   allowBlank: false,
						   afterLabelTextTpl: Mynerp.util.Util.required,						   
						   bind : '{currentCadastro.data}'
						   }
					   ]},
						{
						   xtype: 'container',
						   layout: 'hbox',
						   
						   defaults: {
							   flex: 1,
							   xtype: 'textfield',
							   maxLength: 100,
						   	   enforceMaxLength: true,
						   	   maxLengthText: 'Máximo de {0} caracteres.',
							   msgTarget: 'side',
							   labelWidth: 105,
							   labelAlign: 'top',
							   labelPad: 2,
							   padding: '0 10 10 0'
						   },
						   items: [

						   	   {
						   	   fieldLabel: 'Local de Coleta',
							   maxLength: 100,
							   flex: 6,
							   bind : '{currentCadastro.nomeLocal}',
							   allowBlank: false
							   },
							   {
						   	   fieldLabel: 'Cidade',
							   maxLength: 50,
							   flex: 6,
							   bind : '{currentCadastro.cidade}'
							   },
							   {
						   	   fieldLabel: 'KM',
						   	   xtype: 'numberfield',
							   hideTrigger: true,
						   	   maskRe: /[0-9]/i,
							   flex: 1,
							   bind : '{currentCadastro.quilometragem}'
							   }		   
						   ]
						},
						{
						   xtype: 'container',
						   layout: 'hbox',
						   
						   defaults: {
							   flex: 1,
							   xtype: 'textfield',
							   maxLength: 100,
						   	   enforceMaxLength: true,
						   	   maxLengthText: 'Máximo de {0} caracteres.',
							   msgTarget: 'side',
							   labelWidth: 105,
							   labelAlign: 'top',
							   labelPad: 2,
							   padding: '0 10 10 0'
						   },
						   items: [
							   {
							   fieldLabel: 'Nº Pedido',
							   maxLength: 20,
							   flex: 1,
							   bind : '{currentCadastro.numeroPedido}'
							   },
							   {
							   fieldLabel: 'Nº Nota',
							   maxLength: 20,
							   flex: 1,
							   bind : '{currentCadastro.numeroNota}'
							   },
							   {
							   xtype: 'numberfield',
							   hideTrigger: true,
							   fieldLabel: 'Peso Kg',
							   value: null,
							   flex: 1,
							   bind : '{currentCadastro.peso}'
							   },
							   {
							   xtype: 'numberfield',
							   hideTrigger: true,
							   fieldLabel: 'Volumes',
							   allowDecimals: false,
							   flex: 1,
							   bind : '{currentCadastro.quantidadeVolume}'
							   },
							   {
							   xtype: 'numberfield',
							   hideTrigger: true,
							   fieldLabel: 'Vr.Mercad.',
							   flex: 1,
							   bind : '{currentCadastro.valorMercadoria}'
							   },
							   {
							   fieldLabel: 'Seu Nº',
							   maxLength: 50,
							   flex: 1,
							   bind : '{currentCadastro.seuNumero}'
							   },
							   {
							   xtype: 'numberfield',
							   hideTrigger: true,
							   fieldLabel: 'Nº CT-e',
							   allowDecimals: false,
							   flex: 1,
							   bind : '{currentCadastro.idCTE}'
							   }		   
						   ]
						}
					]
				},
				// fieldset cobrança
				{
				xtype: 'fieldset',
				title: 'Cobrança',

				items: [
					   {
					   xtype: 'container',
					   layout: 'hbox',
					   combineErrors: true,
					   defaults: {
						   flex: 1,
						   xtype: 'textfield',
						   maxLength: 100,
						   enforceMaxLength: true,
						   maxLengthText: 'Máximo de {0} caracteres.',
						   msgTarget: 'side',
						   labelWidth: 105,
						   labelAlign: 'top',
						   labelPad: 2,
						   padding: '0 10 10 0'
					   },
					   items: [
						   {
						   xtype: 'numberfield',
						   hideTrigger: true,
						   fieldLabel: 'Valor Frete',
						   flex: 1,
						   allowBlank: false,
						   afterLabelTextTpl: Mynerp.util.Util.required,						   
						   bind : '{currentCadastro.valorFrete}'
						   },
						   {
						   xtype: 'datefield',
						   fieldLabel: 'Dt. Vencimento',
						   flex: 2,
						    bind : '{currentCadastro.dataVencimento}'
						   },
						   {
							   fieldLabel: 'Código',
							   reference: 'codCobranca',
							   xtype: 'getidfield',
				               xtypeLista: 'lista-cobranca-window',
				               modelName: Mynerp.model.staticData.financeiro.Cobranca,
				               nameFieldReference: 'nomeDaCobranca',						   
							   maskRe: /[0-9]/i,
							   maxLength: 6,
							   flex: 1,
							   allowBlank: false,
							   afterLabelTextTpl: Mynerp.util.Util.required,
							   bind : '{currentCadastro.idCobranca}'
							   },
							   {
							   fieldLabel: 'Cobrança',
							   reference: 'nomeDaCobranca',
							   maxLength: 60,
							   flex: 3,
							   //editable: false,
							   bind : '{currentCadastro.nomeCobranca}'
						   	   },
						   {
						   	xtype: 'checkboxfield',
						   	fieldLabel: ' ',
						   	hideEmptyLabel: false,
						   	flex: 2,
						   	boxLabel: 'Gera conta a receber',
                    		name: 'indCR',
                    		checked: true,
                    		inputValue: 1,
                    		uncheckedValue:0,                    		
                    		bind: '{currentCadastro.indGeraCR}'
						   }//,						   
						 //   {
						 //   	xtype: 'container',
						 //   	name: 'containerVazio',
							// layout: 'anchor',
							// hidden: false,
							// flex: 2
						 //   }
					   ]}
					]
				},

				{ //fieldset observação
				xtype: 'fieldset',
				title: 'Observação',

				items: [
					   {
					   xtype: 'container',
					   layout: 'anchor',
					   combineErrors: true,
					   defaults: {
						   //flex: 1,
						   maxLength: 250,
						   enforceMaxLength: true,
						   maxLengthText: 'Máximo de {0} caracteres.',
						   msgTarget: 'side',
						   labelAlign: 'top',
						   padding: '0 10 10 0'
					   },
					   items: [	                	        	              	        	   
						   {
						   xtype     : 'textareafield',
						   grow      : true,
						   anchor    : '100%',
						   bind : '{currentCadastro.observacao}'
						   }						   	   
					   ]}						
					]
				}
			]
});