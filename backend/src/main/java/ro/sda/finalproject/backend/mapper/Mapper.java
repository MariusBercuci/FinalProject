package ro.sda.finalproject.backend.mapper;

public interface Mapper <E, D>{

    public D convertToDto(E entity);

    public E convertToEntity(D dto);
}
