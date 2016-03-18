package com.jerryorr.lightning.dash;

import java.io.IOException;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.ObjectMapper;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

/**
 * Service for pushing data to Dash widgets. See DashChartUpdater and
 * SessionListener for example usage.
 * 
 * @author jerryorr
 */
@Component
public class Push {
	@PostConstruct
	public void setup() {
		Unirest.setObjectMapper(new ObjectMapper() {
			private com.fasterxml.jackson.databind.ObjectMapper jacksonObjectMapper = new com.fasterxml.jackson.databind.ObjectMapper();

			public <T> T readValue(String value, Class<T> valueType) {
				try {
					return jacksonObjectMapper.readValue(value, valueType);
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
			}

			public String writeValue(Object value) {
				try {
					return jacksonObjectMapper.writeValueAsString(value);
				} catch (JsonProcessingException e) {
					throw new RuntimeException(e);
				}
			}
		});
	}

	public UrlStep start() {
		return new UrlStep(new PushData());
	}

	private static final class PushData {
		private String url;
		private String contentType;
		private Object data;
	}

	public static final class UrlStep {
		private PushData d;

		private UrlStep(PushData d) {
			this.d = d;
		}

		public ContentStep url(String url) {
			d.url = url;
			return new ContentStep(d);
		}
	}

	public static final class ContentStep {
		private PushData d;

		private ContentStep(PushData d) {
			this.d = d;
		}

		public Pusher plain(Object data) {
			d.data = data;
			d.contentType = "text/plain";
			return new Pusher(d);
		}

		public Pusher json(Object data) {
			d.data = data;
			d.contentType = "application/json";
			return new Pusher(d);
		}

		public Pusher chart(Chart chart) {
			return json(Chart.json(chart));
		}

		public Pusher speedometer(Speedometer s) {
			return json(s);
		}
	}

	public static final class Pusher {
		private PushData d;

		private Pusher(PushData d) {
			this.d = d;
		}

		public void push() throws PushException {
			try {
				HttpResponse<JsonNode> resp = Unirest.post(d.url)
						.header("content-type", d.contentType).body(d.data).asJson();

				if (resp.getStatus() != 200) {
					throw new PushException(String.format("Error attempting to push to Dash: status=%d, message=%s",
							resp.getStatus(), resp.getBody().getObject().getString("message")));
				}
			} catch (UnirestException e) {
				throw new PushException("Error attempting to push to Dash", e);
			}
		}
	}
}
