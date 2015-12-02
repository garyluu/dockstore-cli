package io.dockstore.webservice.helpers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.client.HttpClient;

import io.dockstore.webservice.core.Container;
import io.dockstore.webservice.core.Tag;
import io.dockstore.webservice.core.Token;

import static io.dockstore.webservice.helpers.ImageRegistryFactory.Registry;

/**
 * A no-op interface intended as a place-holder for where we will implemnt docker hub functionality when they get around to exposing and
 * implementing their API.
 * 
 * @author dyuen
 */
public class DockerHubRegistry implements ImageRegistryInterface {

    private final HttpClient client;

    public DockerHubRegistry(HttpClient client) {
        this.client = client;
    }

    @Override
    public List<Tag> getTags(Container container) {
        return new ArrayList<>();
    }

    @Override
    public List<String> getNamespaces() {
        return new ArrayList<>();
    }

    @Override
    public List<Container> getContainers(List<String> namespaces) {
        return new ArrayList<>();
    }

    @Override
    public Map<String, ArrayList<?>> getBuildMap(Token githubToken, Token bitbucketToken, List<Container> allRepos) {
        // Go through each container for each namespace
        for (final Container container : allRepos) {
            if (!container.getRegistry().equals(Registry.DOCKER_HUB.toString())){
                continue;
            }

            final SourceCodeRepoInterface sourceCodeRepo = SourceCodeRepoFactory.createSourceCodeRepo(container.getGitUrl(), client,
                bitbucketToken == null ? null : bitbucketToken.getContent(), githubToken.getContent());
            if (sourceCodeRepo != null) {
                // find if there is a Dockstore.cwl file from the git repository
                sourceCodeRepo.findCWL(container);
            }
        }
        return new HashMap<>();
    }
}