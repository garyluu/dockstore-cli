package io.dockstore.webservice.helpers;

import io.dockstore.webservice.core.Container;
import io.dockstore.webservice.core.Tag;
import io.dockstore.webservice.core.Token;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Interface for how to grab data from a registry for docker containers.
 *
 * TODO: A pretty poor abstraction, there are quay.io
 * data structures in here, one step at a time
 * 
 * @author dyuen
 */
public interface ImageRegistryInterface {

    /**
     * Get all tags for a given container
     * 
     * @return a list of tags for image that this points to
     */
    List<Tag> getTags(Container container);

    /**
     * Get the list of namespaces and organizations that the user is associated to on Quay.io.
     *
     * @return list of namespaces
     */
    List<String> getNamespaces();

    /**
     * Get all containers from provided namespaces
     * @param namespaces
     * @return
     */
    List<Container> getContainers(List<String> namespaces);

    /**
     *
     * @param githubToken
     * @param bitbucketToken
     * @param allRepos a list of images that gets modified with data from builds like data modified, size, etc.
     * @return map of path -> list of quay.io build data structure
     */
    Map<String, ArrayList<?>> getBuildMap(Token githubToken, Token bitbucketToken, List<Container> allRepos);
}
