__author__ = 'alberto'
from datetime import datetime


def create_and_add_mapping(connection, index_name, type_name):
    try:
        connection.indices.create(index_name)
    except:
        # we skip exception if index already exists
        pass
    connection.cluster.health(wait_for_status="yellow")

    type2 = type_name + "2"
    connection.indices.put_mapping(index=index_name, doc_type=type2, body={type2: {
        "_parent": {
            "type": "my_type"
        },
        "_routing": {
            "required": True
        },
        "properties": {
            "name": {"type": "text", "store": "true", "term_vector": "with_positions_offsets", "fielddata": "true"},
            "value": {
                "type": "text"
            }
        }
    }})

    connection.indices.put_mapping(index=index_name, doc_type=type_name, body={type_name:{"properties": {
    "uuid": {"type": "keyword", "store": "true"},
    "title": {"type": "text", "store": "true", "term_vector": "with_positions_offsets"},
    "parsedtext": { "type": "text", "store": "true", "term_vector": "with_positions_offsets", "fielddata": "true"},
    "nested": {"type": "nested", "properties": {"num": {"type": "integer", "store": "true"},
                                                "name": {"type": "keyword", "store": "true"},
                                                "value": {"type": "keyword", "store": "true"}}},
    "date": {"type": "date", "store": "true"},
    "position": {"type": "integer", "store": "true"},
    "name": {"type": "text", "store": "yes", "term_vector": "with_positions_offsets", "fielddata": "true"}}}})



def populate(connection, index_name, type_name):
    connection.index(index=index_name, doc_type=type_name, id=1,
                     body={"name": "Joe Tester", "parsedtext": "Joe Testere nice guy", "uuid": "11111",
                           "position": 1,
                           "date": datetime(2013, 12, 8)})
    connection.index(index=index_name, doc_type=type_name + "2", id=1, body={"name": "data1", "value": "value1"},
                     parent=1)
    connection.index(index=index_name, doc_type=type_name, id=2,
                     body={"name": "Bill Baloney", "parsedtext": "Bill Testere nice guy", "uuid": "22222",
                           "position": 2,
                           "date": datetime(2013, 12, 8)})
    connection.index(index=index_name, doc_type=type_name + "2", id=2, body={"name": "data2", "value": "value2"},
                     parent=2)
    connection.index(index=index_name, doc_type=type_name, id=3, body={"name": "Bill Clinton", "parsedtext": """Bill is not
    nice guy""", "uuid": "33333", "position": 3, "date": datetime(2013, 12, 8)})

    connection.indices.refresh(index_name)