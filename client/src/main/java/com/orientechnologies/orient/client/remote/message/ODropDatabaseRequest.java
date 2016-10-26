package com.orientechnologies.orient.client.remote.message;

import java.io.IOException;

import com.orientechnologies.orient.client.binary.OChannelBinaryAsynchClient;
import com.orientechnologies.orient.client.remote.OBinaryRequest;
import com.orientechnologies.orient.client.remote.OStorageRemoteSession;
import com.orientechnologies.orient.enterprise.channel.binary.OChannelBinary;
import com.orientechnologies.orient.enterprise.channel.binary.OChannelBinaryProtocol;

public class ODropDatabaseRequest implements OBinaryRequest {
  private String databaseName;
  private String storageType;

  public ODropDatabaseRequest(String databaseName, String storageType) {
    this.databaseName = databaseName;
    this.storageType = storageType;
  }

  public ODropDatabaseRequest() {
  }

  @Override
  public void write(OChannelBinaryAsynchClient network, OStorageRemoteSession session, int mode) throws IOException {
    network.writeString(databaseName);
    network.writeString(storageType);
  }

  @Override
  public void read(OChannelBinary channel, int protocolVersion, String serializerName) throws IOException {
    databaseName = channel.readString();
    storageType = channel.readString();
  }

  public String getDatabaseName() {
    return databaseName;
  }

  public String getStorageType() {
    return storageType;
  }

  @Override
  public byte getCommand() {
    return OChannelBinaryProtocol.REQUEST_DB_DROP;
  }
}