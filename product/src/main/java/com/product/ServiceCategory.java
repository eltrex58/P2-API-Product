package com.product;

import java.util.List;
import java.util.stream.Collectors;

import com.product.exception.ServiceCategoryException;

import java.util.ArrayList;

/**
 * Clase que representa el servicio de gestión de categorías.
 */
public class ServiceCategory {

    /*
     * Lista que almacena las categorías registradas en el sistema.
     */
    private List<Category> categories = new ArrayList<>();
    /*
     * Variable que mantiene el siguiente ID disponible para una nueva categoría.
     */
    private int nextID = 1;


    /**
     * Constructor por defecto de la clase ServiceCategory.
     */
    public ServiceCategory(){}

    /**
     * Método para obtener todas las categorías registradas en el sistema.
     * @return Una cadena que representa todas las categorías en formato JSON.
     * @throws ServiceCategoryException Si no existen categorías registradas.
     */
    public String getCategories(){
        requireNotEmpty();
        return this.toString();
    }

    /**
     * Método para obtener las categorías hijas de una categoría específica.
     * @param parentId El ID de la categoría padre.
     * @return Una cadena que representa las categorías hijas en formato JSON.
     * @throws ServiceCategoryException Si el ID del padre es nulo o si no existen categorías registradas.
     */
    public String getChildCategories(Integer parentId){
        if (parentId == null)
            throw new ServiceCategoryException("Null no es un ID válido.");
        requireNotEmpty();        
        return categories.stream()
            .filter(c -> parentId.equals(c.getParentCategoryId()))
            .map(Category::toString)
            .collect(Collectors.joining(",", "[", "]"));
    }

    /**
     * Método para crear una nueva categoría en el sistema.
     * @param newCategory La categoría a crear.
     * @throws ServiceCategoryException Si la categoría ya existe o si el padre no es válido.
     */
    public void createCategory(Category newCategory){
        Integer parentID = newCategory.getParentCategoryId();

        if (parentID != null) {
            verify(newCategory, parentID);
        }

        if (exists(newCategory)) {
            throw new ServiceCategoryException("La categoría que desea agregar ya exite en la lista.");
        }

        addCategory(newCategory);
    }

    /**
     * Método privado para verificar si una categoría es válida.
     * @param newCategory La categoría a verificar.
     * @param parentID El ID de la categoría padre.
     * @throws ServiceCategoryException Si la categoría no es válida.
     */
    private void verify (Category newCategory, Integer parentID) {
        if (!isParentValid(parentID)) {
            throw new ServiceCategoryException("No existe el padre del que quiere heredar.");
        }

        if (parentID.equals(nextID)) {
            throw new ServiceCategoryException("Una categoría no puede ser padre de sí misma.");
        }
    }
        
    /**
     * Método privado para verificar si una categoría ya existe en el sistema.
     * @param newCategory La categoría a verificar.
     * @return true si la categoría ya existe, false en caso contrario.
     */
    private boolean exists(Category newCategory){
        return categories.stream()
            .anyMatch(c -> c.getCategory().equals(newCategory.getCategory()) || 
                            c.getTag().equals(newCategory.getTag()));
    }

    /**
     * Método privado para agregar una nueva categoría al sistema.
     * @param newCategory La categoría a agregar.
     */
    private void addCategory(Category newCategory){
        newCategory.setCategoryId(nextID++);
        newCategory.setStatus(1);
        categories.add(newCategory);
    }

    private boolean isParentValid(Integer parentId){
        return categories.stream().anyMatch(c -> parentId.equals(c.getCategoryId()));
    }

    /**
     * Método para eliminar una categoría del sistema.
     * @param categoryId El ID de la categoría a eliminar.
     * @throws ServiceCategoryException Si la categoría no existe o si tiene categorías hijas.
     */
    public void deleteCategory(Integer categoryId){
        if (categoryId.compareTo(nextID) >= 0 || categoryId.compareTo(1) < 0 || 
            categories.stream().
                anyMatch(c -> c.getStatus().equals(0) && c.getCategoryId().equals(categoryId))) {
            throw new ServiceCategoryException("No se puede eliminar la categoría porque no existe.");
        }
        if(isParent(categoryId)) {
            throw new ServiceCategoryException("No se puede eliminar la categoría porque tiene categorías hijas.");
        }
        categories.stream()
            .filter(c -> c.getCategoryId().equals(categoryId))
            .findFirst()
            .ifPresent(c -> c.setStatus(0));
    }

    /**
     * Método privado para verificar si una categoría es padre de otras categorías.
     * @param parentId El ID de la categoría a verificar.
     * @return true si la categoría es padre, false en caso contrario.
     */
    private boolean isParent (Integer parentId){
        return categories.stream().anyMatch(c -> parentId.equals(c.getParentCategoryId()));
    }

    /**
     * Método privado para verificar si existen categorías registradas en el sistema.
     * @throws ServiceCategoryException Si no existen categorías registradas.
     */
    private void requireNotEmpty(){
        if (categories.isEmpty() || categories.stream().allMatch(c -> c.getStatus().equals(0)))
            throw new ServiceCategoryException("No existen categorías registradas.");
    }

    /**
     * Método para representar todas las categorías en forma de String. Se representan como un arreglo JSON 
     * con los atributos de cada categoría.
     * @return La representación de todas las categorías en forma de String.
     */
    @Override
    public String toString() {
        return categories.stream()
                .filter(category -> category.getStatus() == 1)
                .map(Category::toString)
                .collect(Collectors.joining(", ", "[", "]"));
    }
}