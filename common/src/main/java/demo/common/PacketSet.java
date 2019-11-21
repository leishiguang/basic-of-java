package demo.common;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * 标准报文合集，应对的场景是登记系统表数据，或者第三方报文数据，数据与数据之间的关联关系十分紧密。则使用这个容器，将报文关系抹平，只保留一个层级的 list； 并提供了 select
 * 方法用于查找该报文合集中的数据，提供了 list 方法将合集中的数据转换成想要的 list 类型；
 *
 * @author leishiguang
 * @since v1.0
 */
public class PacketSet<E> extends ArrayList<Object> {

  private Class<E> clazz;
  private final String mark = "type";

  /**
   * 往集合中添加数据，最终会转成 jsonObject 格式存入 List。在把 Object 转换成 jsonObject 的过程中，会添加名为 “type” 的字段，用于反序列化时的标记；
   * 添加的依据是取简化类型，即 {@link Class#getSimpleName()}，如果添加的是 List 类型数据，则会将 List 转换成 jsonArray 之后循环转成
   * jsonObject
   *
   * @param object 任意格式数据，如：登簿报文中，添加的是不动产单元的各类表
   */
  @Override
  public boolean add(Object object) {
    String type = object.getClass().getSimpleName();
    Object json = JSON.toJSON(object);
    if (json instanceof JSONObject) {
      return this.add(type, (JSONObject) json);
    }
    if (json instanceof JSONArray) {
      JSONArray jsonArray = (JSONArray) json;
      for (int i = 0; i < jsonArray.size(); i++) {
        if (!this.add(type, jsonArray.getJSONObject(i))) {
          return false;
        }
      }
      return true;
    }
    throw new RuntimeException("无法往报文集合中添加数据");
  }

  /**
   * json 对象直接增加，不再进行格式转换
   */
  private void add(JSONObject jsonObject) {
    super.add(jsonObject);
  }

  /**
   * json 对象增加时，添加格式标记
   */
  private boolean add(String type, JSONObject jsonObject) {
    jsonObject.put(mark, type);
    return super.add(jsonObject);
  }

  public <T> PacketSet<T> select(Class<T> clazz) {
    String type = clazz.getSimpleName();
    PacketSet<T> packetSet = new PacketSet<>();
    packetSet.clazz = clazz;
    for (Object object : this) {
      JSONObject packet = (JSONObject) object;
      if (type.equals(packet.getString(mark))) {
        packetSet.add(packet);
      }
    }
    return packetSet;
  }

  /**
   * 查询符合条件的 List
   *
   * @param propertyKey   属性key
   * @param propertyValue 属性键值
   */
  public PacketSet<E> select(String propertyKey, Object propertyValue) {
    Map<String, Object> queryMap = new HashMap<>(6);
    queryMap.put(propertyKey, propertyValue);
    return this.select(queryMap);
  }

  public E selectOne(String propertyKey, Object propertyValue) {
    return this.select(propertyKey, propertyValue).one();
  }

  /**
   * 多个条件复合查询，是 {@link PacketSet#select(String, Object)}的增强版本
   */
  public PacketSet<E> select(Map<String, Object> queryMap) {
    if (clazz == null) {
      System.out.println("请先执行 select(Class) 之后，再查询对应属性。");
    }
    PacketSet<E> packetSet = new PacketSet<>();
    packetSet.clazz = clazz;
    Set<Entry<String, Object>> entrySet = queryMap.entrySet();
    for (Object object : this) {
      JSONObject packet = (JSONObject) object;
      if (contain(packet, entrySet)) {
        packetSet.add(packet);
      }
    }
    return packetSet;
  }

  public E selectOne(Map<String, Object> queryMap) {
    List<E> collection = this.select(queryMap).list();
    if (collection.size() != 0) {
      return collection.get(0);
    }
    return null;
  }


  private boolean contain(JSONObject packet, Set<Entry<String, Object>> entrySet) {
    for (Entry<String, Object> entry : entrySet) {
      if (!entry.getValue().equals(packet.getObject(entry.getKey(), entry.getValue().getClass()))) {
        return false;
      }
    }
    return true;
  }


  public List<E> list() {
    if (clazz == null) {
      System.out.println("没有对ResultPacketCollection进行格式转换，返回的是JsonObject元素。建议在 select(Class) 之后再进行 list()");
      return (List) this;
    }
    return list(clazz);
  }

  public List<E> list(Class<E> clazz) {
    List<E> newList = new ArrayList<>();
    for (Object object : this) {
      newList.add(JSONObject.toJavaObject((JSONObject) object, clazz));
    }
    return newList;
  }

  public E one() {
    if (clazz == null) {
      System.out.println("没有对ResultPacketCollection进行格式转换，返回的是JsonObject元素。建议在 select(Class) 之后再进行 list()");
    }
    if (this.size() == 0) {
      return null;
    }
    return this.list().get(0);
  }


}
