Ext.define('Mynerp.view.configuracao.ParametroColetaForm', {
	extend: 'Mynerp.view.configuracao.base.BaseForm',
	xtype: 'parametro-coleta-form',

	requires: [
   		'Mynerp.util.Util',
   		'Mynerp.util.Glyphs',
   		'Mynerp.model.staticData.financeiro.Cobranca'
   ],

	bodyPadding: 10,
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
		{
		xtype: 'fieldset',
		title: 'Faturamento',

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
						   padding: '0 10 10 0'
					   },
			   items: [{
				   name: 'id',
				   editable: false,
				   bind : '{currentCadastro.id}',
				   hidden: true
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
				   //allowBlank: false,
				   afterLabelTextTpl: Mynerp.util.Util.required,
				   bind : '{currentCadastro.idCobranca}'
				   },
				   {
				   fieldLabel: 'Cobrança',
				   reference: 'nomeDaCobranca',
				   maxLength: 60,
				   flex: 5,
				   editable: false,
				   bind : '{currentCadastro.nomeCobranca}'
			   	   },

			   	   {
				   fieldLabel: 'Código',
				   reference: 'codCondicao',
				   xtype: 'getidfield',
	               xtypeLista: 'lista-condicao-window',
	               modelName: Mynerp.model.staticData.financeiro.CondicaoPagamento,
	               nameFieldReference: 'nomeDaCondicao',						   
				   maskRe: /[0-9]/i,
				   maxLength: 6,
				   flex: 1,
				   //allowBlank: false,
				   afterLabelTextTpl: Mynerp.util.Util.required,
				   bind : '{currentCadastro.idCondicao}'
				   },
				   {
				   fieldLabel: 'Condição de Pagamento',
				   reference: 'nomeDaCondicao',
				   maxLength: 60,
				   flex: 5,
				   editable: false,
				   bind : '{currentCadastro.nomeCondicao}'
			   	   }	   
			   ]
			},
			{
				xtype: 'container',
				name: 'containerVazio',
				layout: 'anchor',
				hidden: false,
				flex: 5
			}]
		},
		{
		xtype: 'fieldset',
		title: 'Conhecimento de Transporte',
		items: [
			   {
			   xtype: 'container',
			   flex: 1,
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
				   padding: '0 10 10 0'
			   },
			   items: [
			   	{
				   fieldLabel: 'Caminho arquivo xml CT-e',
				   maxLength: 50,
				   flex: 5,
				   bind : '{currentCadastro.caminhoXmlCte}'
				   //allowBlank: false
				   }
			   ]},
			   {
				xtype: 'container',
				name: 'containerVazio',
				layout: 'anchor',
				hidden: false,
				flex: 1
			}
			]
		}
			]

});