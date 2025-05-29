package com.example.stringconverter.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.ws.client.core.WebServiceTemplate;

import com.example.stringconverter.webservice.ConversionRequest;
import com.example.stringconverter.webservice.ConversionResponse;

/**
 * 文字列変換処理を行うRESTコントローラークラス
 * 
 * <p>クライアントからのリクエストを受け付け、文字列変換サービスを呼び出します。</p>
 */
@RestController // (1) RESTコントローラーとして登録（@Controller + @ResponseBody）
@RequestMapping // ベースURLマッピング
public class StringConvSoapClientController {

	// SOAP通信を行うためのテンプレート
	private final WebServiceTemplate webServiceTemplate;

	/**
	 * コンストラクタ
	 * @param webServiceTemplate Spring WSのWebサービステンプレート
	 */
	public StringConvSoapClientController(WebServiceTemplate webServiceTemplate) {
		this.webServiceTemplate = webServiceTemplate;
	}

	/**
	 * 文字列変換APIエンドポイント
	 * 
	 * <p>GETリクエストで受け取った文字列を変換し、結果を返します。</p>
	 * 
	 * @param request 変換リクエスト（入力文字列を含む）
	 * @return 
	 * @return 変換結果を含むレスポンス
	 */
	@GetMapping("/conversion") // (2) /conversion パスへのGETリクエストを処理
	@ResponseBody // 戻り値を直接レスポンスボディとして返却
	public ConversionResponse convertString(@ModelAttribute ConversionRequest request) {

		return (ConversionResponse) webServiceTemplate.marshalSendAndReceive(request);

	}
}