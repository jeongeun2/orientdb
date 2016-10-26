package com.orientechnologies.orient.client.remote.message;

import java.io.IOException;

import com.orientechnologies.orient.client.binary.OChannelBinaryAsynchClient;
import com.orientechnologies.orient.client.remote.OBinaryRequest;
import com.orientechnologies.orient.client.remote.OStorageRemoteSession;
import com.orientechnologies.orient.enterprise.channel.binary.OChannelBinary;
import com.orientechnologies.orient.enterprise.channel.binary.OChannelBinaryProtocol;

public class OSetGlobalConfigurationRequest implements OBinaryRequest {
  private String key;
  private String value;

  public OSetGlobalConfigurationRequest(String config, String iValue) {
    key = config;
    value = iValue;
  }

  public OSetGlobalConfigurationRequest() {
  }

  @Override
  public void write(OChannelBinaryAsynchClient network, OStorageRemoteSession session, int mode) throws IOException {
    network.writeString(key);
    network.writeString(value);
  }

  @Override
  public void read(OChannelBinary channel, int protocolVersion, String serializerName) throws IOException {
    key = channel.readString();
    value = channel.readString();
  }

  @Override
  public byte getCommand() {
    return OChannelBinaryProtocol.REQUEST_CONFIG_SET;
  }

  public String getKey() {
    return key;
  }

  public String getValue() {
    return value;
  }
}